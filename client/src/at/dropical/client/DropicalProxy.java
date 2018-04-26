package at.dropical.client;

import at.dropical.client.transmitter.RemoteTransmitter;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.Transmitter;
import at.dropical.shared.net.container.CountDownContainer;
import at.dropical.shared.net.container.GameDataContainer;
import at.dropical.shared.net.container.GameOverContainer;
import at.dropical.shared.net.container.ListDataContainer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.Socket;

public final class DropicalProxy implements Runnable {

    @NotNull
    private Transmitter transmitter;
    private DropicalHandler dropicalHandler;


    public DropicalProxy(String host, int port, DropicalHandler dropicalHandler) throws IOException {
//        if(host.equals("localhost")||host.equals("127.0.0.1"))
//            transmitter=new LocalTransmitter(new LocalRequestCache());
//        else
        transmitter = new RemoteTransmitter(new Socket(host, port));
        this.dropicalHandler=dropicalHandler;
        new Thread(this).start();
    }

    public void writeToServer(Request request) {
        transmitter.writeRequest(request);
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Container container=(Container) transmitter.readRequest();

                switch (container.getCurrentState()){
                    case STARTING:
                        dropicalHandler.countDown((CountDownContainer) container);
                        break;
                    case LOBBY:
                        dropicalHandler.somebodyJoinedTheLobby((ListDataContainer) container);
                        break;
                    case RUNNING:
                    case PAUSE:
                        dropicalHandler.updateUI((GameDataContainer) container);
                        break;
                    case GAME_LOST:
                    case GAME_WON:
                    case GAME_OVER:
                        dropicalHandler.onGameOver((GameOverContainer) container);
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
