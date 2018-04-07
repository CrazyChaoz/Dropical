package at.dropical.server;


import at.dropical.shared.net.transmitter.ObjectTransmitter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteAccepterLoop extends Thread{
    private static int port;


    public RemoteAccepterLoop(int port) {
        this.port = port;

        //"Ends" Serving when a negative number was received
        if(port>=0)
            this.start();
    }

    @Override
    public void run() {
        try(Socket clientConnection=new ServerSocket(port).accept();
            InputStream inputStream=clientConnection.getInputStream();
            OutputStream outputStream=clientConnection.getOutputStream()){

            //create new open connection on creation of a new open connection
            new RemoteAccepterLoop(port);


            //<CODE>
            //add new connection to Server
            ObjectTransmitter transi=new ObjectTransmitter(inputStream,outputStream);
            Server.instance().getConnected().add(transi);


            //Error if not in loop
            for(;;){
                new ServerSideRequestHandler(transi.readRequest(),transi);
            }
            //<!CODE>
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
