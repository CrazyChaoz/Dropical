package at.dropical.server;


import at.dropical.shared.net.transmitter.ObjectTransmitter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteAccepterLoop extends Thread{
    private static ServerSocket serverSocket;


    public RemoteAccepterLoop(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;

        //"Ends" Serving when a "null" Socket was received
        if(serverSocket!=null)
            this.start();
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
