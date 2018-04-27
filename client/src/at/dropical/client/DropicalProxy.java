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
    private DropicalListener dropicalListener;


    public DropicalProxy(String host, int port, DropicalListener dropicalListener) throws IOException {
//        if(host.equals("localhost")||host.equals("127.0.0.1"))
//            transmitter=new LocalTransmitter(new LocalRequestCache());
//        else
        transmitter = new RemoteTransmitter(new Socket(host, port));
        this.dropicalListener = dropicalListener;
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
                        dropicalListener.countDown((CountDownContainer) container);
                        break;
                    case LOBBY:
                        dropicalListener.somebodyJoinedTheLobby((ListDataContainer) container);
                        break;
                    case RUNNING:
                    case PAUSE:
                        dropicalListener.updateUI((GameDataContainer) container);
                        break;
                    case GAME_LOST:
                    case GAME_WON:
                    case GAME_OVER:
                        dropicalListener.onGameOver((GameOverContainer) container);
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
