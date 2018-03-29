package at.dropical.shared.net.requests;

public class JoinRequest implements Request {
    private String gameID;
    private boolean isPlayer;
    private String playerName;

    public JoinRequest(String gameID, String playerName) {
        this.gameID = gameID;
        this.isPlayer = true;
        this.playerName = playerName;
    }

    public JoinRequest(String gameID) {
        this.gameID = gameID;
        this.isPlayer = false;
        this.playerName = null;
    }

    public String getGameID() {
        return gameID;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public String getPlayerName() {
        return playerName;
    }
}
