package at.dropical.server.net;

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

            //Reader r=new InputStreamReader(inputStream);
//TODO: Server.addTransmitter(new HumanTransmitter(inputStream,outputStream));

            //<CODE>
            //integration of client into the server


            //info.getGame() returns String
            //server: has instances of every game
//            game=info.getGame()
//            if(info.isPlayer)
//                game.addPlayer(info.getPlayerName());
//            else
//                game.addViewer();


            for(;;){

            }
            //<!CODE>
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
