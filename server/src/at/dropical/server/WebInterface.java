package at.dropical.server;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;

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
                Server.execute(new WebInterfaceClass(serverSocket.accept()));
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
                            new ScriptingEngine(out,"index");
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
    StringBuilder stringBuilder = new StringBuilder();

    public ScriptingEngine(BufferedOutputStream out, String command) throws IOException {

        loadTemplate();

        String retval;
        String[] cmdParts = command.replaceFirst(" ", "%20").split("%20");
        switch (cmdParts[0]) {
            case "index":
                retval = showIndex();
                break;
            case "listOpenGames":
                retval = listGamesByName();
                break;
            case "examine":
                retval = examineGame(cmdParts[1]);
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

    private void loadTemplate() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + File.separator + "Resources" + File.separator + "Template.dropical")));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\r\n");
            }
        } catch (FileNotFoundException e) {
            Server.logger().log(Level.WARNING, "Template.html not found");
        } catch (IOException e) {
            Server.logger().log(Level.WARNING, "Template.html -- io error");
        }
    }

    public String showIndex() {



        stringBuilder.append("<h2>Dropical LandingPages</h2>\r\n");
        stringBuilder.append("<a id='game' href='listOpenGame'>List Open Games</a><br>\r\n");
        stringBuilder.append("</body>\r\n");
        stringBuilder.append("</html>\r\n");
        return stringBuilder.toString();
    }

    public String listGamesByName() {

        final Set<Map.Entry<String, Game>> entries = Server.instance().getManager().getAllGames().entrySet();

        if (entries.size() <= 0)
            stringBuilder.append("<h2>There is currently no Game running</h2>");
        else if (entries.size() == 1)
            stringBuilder.append("<h2>There is currently one Game running</h2>");
        else {
            stringBuilder.append("<h2>There are currently ");
            stringBuilder.append(entries.size());
            stringBuilder.append(" Games running</h2>\r\n");
        }

        for (Map.Entry<String, Game> entry : entries) {
            stringBuilder.append("<a id='game' href='$$examine ");
            stringBuilder.append(entry.getKey());
            stringBuilder.append("'>");
            stringBuilder.append(entry.getKey());
            stringBuilder.append("</a><br>\r\n");
        }

        stringBuilder.append("</body>\r\n");
        stringBuilder.append("</html>\r\n");
        return stringBuilder.toString();
    }

    public String examineGame(String gameID) {
        final Game game = Server.instance().getManager().getGame(gameID);

        if (game.getGames().size() <= 0)
            stringBuilder.append("<h2>There is currently no Player connected</h2>");
        else if (game.getGames().size() == 1)
            stringBuilder.append("<h2>There is currently one Player connected</h2>");
        else {
            stringBuilder.append("<h2>There are currently ");
            stringBuilder.append(game.getGames().size());
            stringBuilder.append(" Players connected</h2>\r\n");
        }

        for (Map.Entry<String, OnePlayer> entry : game.getGames().entrySet()) {
            stringBuilder.append("<p id='player'");
            stringBuilder.append(entry.getKey());
            stringBuilder.append("'>");
            stringBuilder.append(entry.getKey());
            stringBuilder.append("</p><br>\r\n");
        }

        stringBuilder.append("</body>\r\n");
        stringBuilder.append("</html>\r\n");
        return stringBuilder.toString();
    }
}