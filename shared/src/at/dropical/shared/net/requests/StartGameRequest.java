package at.dropical.shared.net.requests;

import at.dropical.shared.RequestKind;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.SendableItem;

public class StartGameRequest extends Request{
    private String gameID;

    public StartGameRequest(String gameID) {
        super(RequestKind.START_GAME);
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
