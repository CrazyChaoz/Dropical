package at.dropical.shared.net.requests;

public class AddAiToGameRequest implements Request {
    private String gameID;

    public AddAiToGameRequest(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }
}
