package at.dropical.server;

import at.dropical.server.game.Game;
import at.dropical.shared.net.requests.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class ServerSideTransmitter {
    private Game game;
    private int playerNumber;

    protected InputStream inputStream;
    protected OutputStream outputStream;


    public ServerSideTransmitter(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void setPlayingGame(Game game) {
        this.game = game;
    }

    public Game getPlayingGame() {
        return game;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public abstract void writeRequest(Request r);
    public abstract Request readRequest() throws IOException;
}
