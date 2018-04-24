package at.dropical.shared.net.requests;

import at.dropical.shared.PlayerAction;
import at.dropical.shared.net.abstracts.Request;

public class HandleInputRequest implements Request {
    private PlayerAction input;
    private String playername;

    public HandleInputRequest(String playername,PlayerAction input) {
        this.playername = playername;
        this.input=input;
    }

    public PlayerAction getInput() {
        return input;
    }

    public String getPlayername() {
        return playername;
    }
}
