package at.dropical.client;

import at.dropical.client.transmitter.RemoteTransmitter;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.abstracts.SendableItem;
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
        System.out.println("Connected to "+host+" on Port "+port);
        this.dropicalListener = dropicalListener;
        new Thread(this, "DropicalProxy").start();
    }

    public void writeToServer(SendableItem request) {
        transmitter.writeRequest(request);
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Container container = (Container) transmitter.readRequest();

                if (container != null)
                    if (container.getCurrentState() != null)
                        switch (container.getCurrentState()) {
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
                System.err.println("IO Exception, Stream was closed");
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
