package at.dropical.server;

import at.dropical.server.game.Game;
import at.dropical.server.transmitter.ObjectTransmitter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;


public class WebInterface extends Thread {
    private ServerSocket serverSocket;


    public WebInterface(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.start();
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Server.serverExecutor.execute(new WebInterfaceClass(serverSocket.accept()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class WebInterfaceClass extends Thread {
        Socket client;

        public WebInterfaceClass(Socket client) {
            this.client = client;
            this.start();
        }


        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream())) {

                String s;
                String requested;

                while ((s = in.readLine()) != null) {
                    if (s.matches("(GET|POST) /.* HTTP/1\\.[0|1]")) {
                        requested = s.split(" ")[1];
                        requested = requested.replaceFirst("/", "");
                        if (requested.equals(""))
                            new PresentLandingPage(out);
                        else if (requested.equals("favicon.ico"))
                            new SendFavIcon(out);
                        else if (requested.startsWith("$$")) {
                            new ScriptingEngine(out, requested.replaceFirst("\\$\\$", "").replace("%20", " "));
                        }
                    }
//                    System.out.println(s);
                }


            } catch (IOException e) {
            }
        }
    }
}

class PresentLandingPage {
    public PresentLandingPage(BufferedOutputStream out) throws IOException {

        File file = new File(System.getProperty("user.dir") + File.separator + "Resources" + File.separator + "LandingPage.dropical");

        out.write("HTTP/1.1 200 OK\r\n".getBytes());
        out.write("Server: Java/KempServer\r\n".getBytes());
        out.write("Content Type: text/html\r\n".getBytes());
        out.write(("Content-Length: " + file.length() + "\r\n").getBytes());
        out.write("\r\n".getBytes());

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

        int symbol;
        while ((symbol = bufferedInputStream.read()) != -1) {
            out.write(symbol);
        }
        out.flush();
    }
}

class SendFavIcon {
    public SendFavIcon(BufferedOutputStream out) throws IOException {

        File file = new File(System.getProperty("user.dir") + File.separator + "Resources" + File.separator + "favicon.ico");

        out.write("HTTP/1.1 200 OK\r\n".getBytes());
        out.write("Server: Java/KempServer\r\n".getBytes());
        out.write("Content Type: image/jpeg\r\n".getBytes());
        out.write(("Content-Length: " + file.length() + "\r\n").getBytes());
        out.write("\r\n".getBytes());

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

        int symbol;
        while ((symbol = bufferedInputStream.read()) != -1) {
            out.write(symbol);
        }
        out.flush();
    }
}


class ScriptingEngine {
    public ScriptingEngine(BufferedOutputStream out, String command) throws IOException {
        String retval;
        String[] cmdParts = command.split(" ");
        switch (cmdParts[0]) {
            case "listOpenGames":
                retval = listGamesByName();
                break;
            default:
                retval = "unknown command";
        }


        out.write("HTTP/1.1 200 OK\r\n".getBytes());
        out.write("Server: Java/KempServer\r\n".getBytes());
        out.write("Content Type: text/html\r\n".getBytes());
        out.write(("Content-Length: " + retval.length() + "\r\n").getBytes());
        out.write("\r\n".getBytes());
        out.write(retval.getBytes());
        out.flush();
    }

    public String listGamesByName() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader br=new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + File.separator + "Resources" + File.separator + "Template.html")));
            String line;
            while ((line=br.readLine())!=null)
                stringBuilder.append(line);
        } catch (FileNotFoundException e) {
            Server.LOGGER.log(Level.WARNING,"Template.html not found");
        } catch (IOException e) {
            Server.LOGGER.log(Level.WARNING,"Template.html -- io error");
        }


        final Set<Map.Entry<String, Game>> entries = Server.instance().getAllGames().entrySet();

        stringBuilder.append("<ul>\r\n");
        for (Map.Entry<String, Game> entry : entries) {
            stringBuilder.append("<li>");
            stringBuilder.append(entry.getKey());
            stringBuilder.append("</li>\r\n");
        }
        stringBuilder.append("</ul>\r\n</body>\r\n</html>\r\n");
        return stringBuilder.toString();
    }
}