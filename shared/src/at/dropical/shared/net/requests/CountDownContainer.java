package at.dropical.shared.net.requests;

import at.dropical.shared.GameState;

public class CountDownContainer extends Container {
    private String[] playernames;
    private int seconds;

    public CountDownContainer(String[] playernames, int seconds) {
        super(GameState.GAME_STARTING);
        this.playernames = playernames;
        this.seconds = seconds;
    }

    public String[] getPlayernames() {
        return playernames;
    }

    public int getSeconds() {
        return seconds;
    }

    @Override
    public GameState getCurrentState() {
        return null;
    }
}
