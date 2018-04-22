package at.dropical.shared.net.requests;

import at.dropical.shared.net.abstracts.Request;

public class CreateGameRequest implements Request {
    private String gameName;

    public CreateGameRequest(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }
}
