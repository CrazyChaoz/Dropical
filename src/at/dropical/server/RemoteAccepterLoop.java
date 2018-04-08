package at.dropical.server;


import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.ObjectTransmitter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

public class RemoteAccepterLoop extends Thread{
    private ServerSocket serverSocket;


    public RemoteAccepterLoop(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;

        //"Ends" Serving when a negative number was received
        this.start();
        Server.LOGGER.log(Level.INFO,"new RemoteClientAccepter started");
    }

    @Override
    public void run() {
        try(Socket clientConnection=serverSocket.accept();
            InputStream inputStream=clientConnection.getInputStream();
            OutputStream outputStream=clientConnection.getOutputStream()){

            //create new open connection on creation of a new open connection
            new RemoteAccepterLoop(serverSocket);

            //add new connection to Server
            ObjectTransmitter transi=new ObjectTransmitter(inputStream,outputStream);
            Server.instance().getConnected().add(transi);

            //Error if not in loop
            for(;;){
                new ServerSideRequestHandler(transi.readRequest(),transi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
