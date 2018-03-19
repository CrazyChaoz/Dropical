package at.dropical.shared.net.requests;

public class JoinRequest implements Request {
    private String gameID;
    private boolean isPlayer;
    private String playerName;

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
