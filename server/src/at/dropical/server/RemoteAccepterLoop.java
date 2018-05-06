package at.dropical.server;


import at.dropical.server.transmitter.ObjectTransmitter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class RemoteAccepterLoop extends Thread {
    private ServerSocket serverSocket;
    private ExecutorService executorService = Executors.newCachedThreadPool();


    public RemoteAccepterLoop(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.start();
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                executorService.execute(new InternalAccepter(serverSocket.accept()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class InternalAccepter implements Runnable {
        private Socket socket;

        public InternalAccepter(Socket socket) {
            this.socket=socket;
        }

        @Override
        public void run() {
            try (InputStream inputStream = socket.getInputStream();
                 OutputStream outputStream = socket.getOutputStream()) {

                //add new connection to Server
                ObjectTransmitter transi = new ObjectTransmitter(inputStream, outputStream);

                //Error if not in loop
                for (; ; ) {
                    Server.LOGGER.log(Level.INFO, "Request Received");
                    executorService.execute(new ServerSideRequestHandler(transi.readRequest(), transi));
                }
            } catch (IOException e) {
                Server.LOGGER.log(Level.SEVERE, "IOException, Socket probably disconnected");
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
