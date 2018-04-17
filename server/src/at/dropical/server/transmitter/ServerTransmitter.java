package at.dropical.server.transmitter;

import at.dropical.server.game.Game;
import at.dropical.shared.net.transmitter.Transmitter;

public abstract class ServerTransmitter implements Transmitter {
    private Game currGame;
    private int playerNum;

    public void setCurrGame(Game currGame) {
        this.currGame = currGame;
    }

    public Game getCurrGame() {
        return currGame;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public int getPlayerNum() {
        return playerNum;
    }
}
