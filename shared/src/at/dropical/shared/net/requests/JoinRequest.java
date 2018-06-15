package at.dropical.shared.net.requests;

import at.dropical.shared.RequestKind;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.SendableItem;

public class JoinRequest extends Request {
    private String gameID;
    private String playerName;

    /** TODO Move to GameRequest
     * Instruct the Server to launch a AI and add it to your game.*/
    private boolean playAgainsAI;
    // Only used when a new game is created.
    private int playerCount = 0;

    //for players
    public JoinRequest(String gameID, String playerName) {
        super(RequestKind.JOIN);
        this.gameID = gameID;
        this.playerName = playerName;
        this.playAgainsAI = false;
    }

    //join a random game
    //for tounament
    public JoinRequest(String playerName, int playerCount) {
        super(RequestKind.JOIN);
        this.gameID = null;
        this.playerName = playerName;
        this.playAgainsAI = false;
        this.playerCount = playerCount;
    }

    public JoinRequest(String playerName, boolean playAgainsAI) {
        super(RequestKind.JOIN);
        this.gameID = null;
        this.playerName = playerName;
        this.playAgainsAI = playAgainsAI;
    }

    public JoinRequest(String playerName) {
        super(RequestKind.JOIN);
        this.gameID = null;
        this.playerName = playerName;
        this.playAgainsAI = false;
    }

    public String getGameID() {
        return gameID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean wantsToPlayAgainsAI() {
        return playAgainsAI;
    }

    public int getPlayerCount() {
        return playerCount;
    }
}
