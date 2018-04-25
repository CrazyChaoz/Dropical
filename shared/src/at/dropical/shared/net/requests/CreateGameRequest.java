package at.dropical.shared.net.requests;

import at.dropical.shared.net.abstracts.Request;

public class CreateGameRequest implements Request {
    private String gameName;
    private int maxPlayers;

    public CreateGameRequest(String gameName) {
        this.gameName = gameName;
        this.maxPlayers=2;
    }
    public CreateGameRequest(String gameName,int maxPlayers) {
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
