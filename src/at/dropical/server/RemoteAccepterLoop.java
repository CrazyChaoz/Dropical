package at.dropical.server;


import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.ObjectTransmitter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteAccepterLoop extends Thread{
    private ServerSocket serverSocket;


    public RemoteAccepterLoop(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;

        //"Ends" Serving when a negative number was received
        this.start();
    }

    @Override
    public void run() {
        try(Socket clientConnection=serverSocket.accept();
            InputStream inputStream=clientConnection.getInputStream();
            OutputStream outputStream=clientConnection.getOutputStream()){

            System.out.println("reachable");
            //create new open connection on creation of a new open connection
            new RemoteAccepterLoop(serverSocket);

            //add new connection to Server
            ObjectTransmitter transi=new ObjectTransmitter(inputStream,outputStream);
            System.out.println("reachable");
            Server.instance().getConnected().add(transi);
            System.out.println("reachable");
            //Error if not in loop
            for(;;){
                System.out.println("loop");
                new ServerSideRequestHandler(transi.readRequest(),transi);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
