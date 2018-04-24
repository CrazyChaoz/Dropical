package at.dropical.shared.net.requests;

import at.dropical.shared.net.abstracts.Request;

public class JoinRequest implements Request {
    private String gameID;
    private String playerName;

    //for players
    public JoinRequest(String gameID, String playerName) {
        this.gameID = gameID;
        this.playerName = playerName;
    }

    //join a random game
    //for tounament
    public JoinRequest(String playerName) {
        this.gameID = null;
        this.playerName = playerName;
    }

    public String getGameID() {
        return gameID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
