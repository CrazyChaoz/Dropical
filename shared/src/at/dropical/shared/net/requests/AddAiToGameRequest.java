package at.dropical.shared.net.requests;

import at.dropical.shared.RequestKind;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.SendableItem;

public class AddAiToGameRequest extends Request {
    private String gameID;

    public AddAiToGameRequest(String gameID) {
        super(RequestKind.ADD_AI);
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
