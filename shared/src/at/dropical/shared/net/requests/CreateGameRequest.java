package at.dropical.shared.net.requests;

import at.dropical.shared.RequestKind;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.SendableItem;

public class CreateGameRequest extends Request {
    private String gameName;
    private int maxPlayers;

    public CreateGameRequest(String gameName) {
        super(RequestKind.CREATE_GAME);
        this.gameName = gameName;
        this.maxPlayers=2;
    }
    public CreateGameRequest(String gameName,int maxPlayers) {
        super(RequestKind.CREATE_GAME);
        this.gameName = gameName;
        this.maxPlayers=maxPlayers;
    }

    public String getGameName() {
        return gameName;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}
