package at.dropical.server;

import at.dropical.server.game.Game;
import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class ServerSideTransmitter implements Transmitter {
    private Game game;
    private int playerNumber;

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

}
