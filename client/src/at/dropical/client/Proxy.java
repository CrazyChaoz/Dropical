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
    private GameState currentState = GameState.GAME_LOADING;

    private GameDataContainer gameDataContainer = null;
    private CountDownContainer countDownContainer = null;
    private ListDataContainer listDataContainer = null;
    private GameOverContainer gameOverContainer = null;

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

     @param socket
     */
    public Proxy(Socket socket) {
        transmitter = new RemoteTransmitter(socket);
        this.start();
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Container request = (Container) transmitter.readRequest();

                if (request.getCurrentState() != null)
                    currentState = request.getCurrentState();
                if (request instanceof GameDataContainer)
                    gameDataContainer = (GameDataContainer) request;
                else if (request instanceof CountDownContainer)
                    countDownContainer = (CountDownContainer) request;
                else if (request instanceof ListDataContainer) {
                    listDataContainer = (ListDataContainer) request;

                    if (listDataContainer.getGameNames() != null)
                        for (String s : listDataContainer.getGameNames())
                            System.out.println("Game: " + s);

                } else if (request instanceof GameOverContainer)
                    gameOverContainer = (GameOverContainer) request;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void transmitToServer(Request r) {
        transmitter.writeRequest(r);
    }

    public CountDownContainer getCountDownContainer() {
        return countDownContainer;
    }

    public GameDataContainer getGameDataContainer() {
        return gameDataContainer;
    }

    public GameOverContainer getGameOverContainer() {
        return gameOverContainer;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public ListDataContainer getListDataContainer() {
        return listDataContainer;
    }
}

