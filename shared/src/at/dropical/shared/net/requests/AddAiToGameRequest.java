package at.dropical.shared.net.requests;

import at.dropical.shared.net.abstracts.Request;

public class AddAiToGameRequest implements Request {
    private String gameID;

    public AddAiToGameRequest(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
