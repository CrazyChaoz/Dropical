package at.dropical.shared.net.container;

import at.dropical.shared.GameState;
import at.dropical.shared.net.abstracts.Container;

public class GameOverContainer extends Container {
    private String[] playernames;
    private String gameName;
    private int winnerNumber;

    public GameOverContainer(String[] playernames, String gameName, int winnerNumber) {
        super(GameState.GAME_OVER);
        this.playernames = playernames;
        this.gameName = gameName;
        this.winnerNumber = winnerNumber;
    }

    public String[] getPlayernames() {
        return playernames;
    }

    public String getGameName() {
        return gameName;
    }

    public int getWinnerNumber() {
        return winnerNumber;
    }
}
