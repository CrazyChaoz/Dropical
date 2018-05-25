package at.dropical.server.transmitter;

import at.dropical.server.game.Game;
import at.dropical.shared.net.abstracts.Transmitter;

public abstract class ServerSideTransmitter implements Transmitter {
    private Game game;
    private int playerNumber;
    private boolean isDisconnected=false;

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

    public boolean isDisconnected() {
        return isDisconnected;
    }

    public void setDisconnected() {
        isDisconnected = true;
    }
}
