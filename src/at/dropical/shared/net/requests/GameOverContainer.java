package at.dropical.shared.net.requests;

public class GameOverContainer {
    private String[] playernames;
    private String gameName;
    private int winnerNumber;

    public GameOverContainer(String[] playernames, String gameName, int winnerNumber) {
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
