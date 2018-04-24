package at.dropical.client;

import at.dropical.shared.GameState;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.container.CountDownContainer;
import at.dropical.shared.net.container.GameDataContainer;
import at.dropical.shared.net.container.GameOverContainer;
import at.dropical.shared.net.container.ListDataContainer;
import at.dropical.shared.net.abstracts.Transmitter;

import java.io.IOException;
import java.net.Socket;


public class Proxy extends Thread {
    private GameState currentState = GameState.LOADING;

    private GameDataContainer gameDataContainer = null;

    private Transmitter transmitter;

    /**
     * local
     * W.I.P
     */
    public Proxy() {
//        this.transmitter = transmitter;
        this.start();
    }

    /**
     * @param socket
     */
    public Proxy(Socket socket) {
        transmitter = new RemoteTransmitter(socket);
        this.start();
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Container container = (Container) transmitter.readRequest();
                if (container.getCurrentState() != null) {
                    currentState = container.getCurrentState();
                    switch (container.getCurrentState()) {
                        case LOBBY:
                            System.out.println("#########");
                            for (String s : ((ListDataContainer) container).getGameNames())
                                System.out.println("Spieler: " + s);
                            System.out.println("#########");
                            break;
                        case GAME_LIST:
                            System.out.println("#########");
                            for (String s : ((ListDataContainer) container).getGameNames())
                                System.out.println("Game: " + s);
                            System.out.println("#########");
                            break;
                        case PAUSE:
                        case RUNNING:
                            gameDataContainer = (GameDataContainer) container;
                            System.out.println("GameDataContainer recieved");
                            break;
                        case STARTING:
                            System.out.println("Time until Start: " + ((CountDownContainer) container).getSeconds());
                            break;
                        case GAME_OVER:
                        case GAME_WON:
                        case GAME_LOST:
                            System.out.println("Game is Over");
                            break;

                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void transmitToServer(Request r) {
        transmitter.writeRequest(r);
    }

    public GameDataContainer getGameDataContainer() {
        return gameDataContainer;
    }

    public GameState getCurrentState() {
        return currentState;
    }

}

