package at.dropical.server.net;

import at.dropical.server.Server;
import at.dropical.server.net.transmitter.HumanTransmitter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class AccepterLoop extends Thread{
    private static int port;


    public AccepterLoop(int port) {
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
            new AccepterLoop(port);


            //<CODE>
            //add new connection to Server
            HumanTransmitter transi=new HumanTransmitter(inputStream,outputStream);
            Server.exe().getConnected().add(transi);



            //info.getGame() returns String
            //server: has instances of every game
//            game=info.getGame()
//            if(info.isPlayer)
//                game.addPlayer(info.getPlayerName());
//            else
//                game.addViewer();


            for(;;){
                new RequestHandler(transi.readRequest());
            }
            //<!CODE>
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
