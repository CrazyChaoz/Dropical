package at.dropical.shared.net.requests;

import at.dropical.shared.RequestKind;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.SendableItem;

public class JoinRequest extends Request {
    private String gameID;
    private String playerName;

    //for players
    public JoinRequest(String gameID, String playerName) {
        super(RequestKind.JOIN);
        this.gameID = gameID;
        this.playerName = playerName;
    }

    //join a random game
    //for tounament
    public JoinRequest(String playerName) {
        super(RequestKind.JOIN);
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
