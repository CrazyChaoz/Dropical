package at.dropical.shared.net.requests;

public class CreateGameRequest implements Request{
    private String gameName;

    public CreateGameRequest(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }
}
