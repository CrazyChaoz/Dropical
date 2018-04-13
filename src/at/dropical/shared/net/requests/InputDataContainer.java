package at.dropical.shared.net.requests;

import at.dropical.shared.PlayerAction;

public class InputDataContainer implements Request {
    private PlayerAction input;
    private String playername;

    public InputDataContainer(String playername) {
        this.playername = playername;
    }

    public void setInput(PlayerAction input) {
        this.input = input;
    }

    public PlayerAction getInput() {
        return input;
    }

    public String getPlayername() {
        return playername;
    }
}
