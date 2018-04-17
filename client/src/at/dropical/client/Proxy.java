package at.dropical.client;

import at.dropical.client.remote.RemoteTransmitter;
import at.dropical.shared.GameState;
import at.dropical.shared.net.requests.*;
import at.dropical.shared.net.transmitter.Transmitter;

import java.io.IOException;
import java.net.Socket;

public class Proxy extends Thread {
    private GameState currentState=GameState.GAME_LOADING;

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
     * remote
     *
     * @param socket
     * @throws IOException
     */
    public Proxy(Socket socket) throws IOException {
        transmitter = new RemoteTransmitter(socket);
        this.start();
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                Container request = (Container) transmitter.readRequest();

                if(request.getCurrentState()!=null)
                    currentState=request.getCurrentState();


                if (request instanceof GameDataContainer)
                    gameDataContainer = (GameDataContainer) request;
                else if(request instanceof CountDownContainer)
                    countDownContainer = (CountDownContainer) request;
                else if(request instanceof ListDataContainer)
                    listDataContainer = (ListDataContainer) request;
                else if(request instanceof GameOverContainer)
                    gameOverContainer = (GameOverContainer) request;




            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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
