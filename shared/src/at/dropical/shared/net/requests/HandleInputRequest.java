package at.dropical.shared.net.requests;

import at.dropical.shared.PlayerAction;
import at.dropical.shared.RequestKind;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.SendableItem;

public class HandleInputRequest extends Request {
    private PlayerAction input;
    private String playername;

    public HandleInputRequest(String playername,PlayerAction input) {
        super(RequestKind.HANDLE_INPUT);
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
