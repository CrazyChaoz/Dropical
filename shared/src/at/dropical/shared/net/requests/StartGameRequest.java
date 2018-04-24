package at.dropical.shared.net.requests;

import at.dropical.shared.net.abstracts.Request;

public class StartGameRequest implements Request {
    private String gameID;

    public StartGameRequest(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
