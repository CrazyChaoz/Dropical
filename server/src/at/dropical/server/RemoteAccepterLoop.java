package at.dropical.server;


import at.dropical.server.transmitter.ObjectTransmitter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

public class RemoteAccepterLoop extends Thread {
    private ServerSocket serverSocket;


    public RemoteAccepterLoop(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.start();
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Server.serverExecutor.execute(new InternalAccepter(
                        serverSocket.accept()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class InternalAccepter implements Runnable {
        private Socket socket;

        public InternalAccepter(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (InputStream inputStream = socket.getInputStream();
                 OutputStream outputStream = socket.getOutputStream()) {

                new Loop(new ObjectTransmitter(inputStream, outputStream));

            } catch (IOException e) {
                Server.LOGGER.log(Level.SEVERE, "IOException, Socket probably disconnected");
            } catch (ClassNotFoundException e) {
                Server.LOGGER.log(Level.SEVERE, "ClassNotFoundException ..... Faulty Client!");
            } catch (ClassCastException e) {
                Server.LOGGER.log(Level.SEVERE, "ClassCastException -- class received is not a subclass of Request");
            }
        }
    }
}
