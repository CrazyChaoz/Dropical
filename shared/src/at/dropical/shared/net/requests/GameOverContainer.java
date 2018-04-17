package at.dropical.shared.net.requests;

import at.dropical.shared.GameState;

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
