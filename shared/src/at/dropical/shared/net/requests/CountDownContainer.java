package at.dropical.shared.net.requests;

import at.dropical.shared.GameState;

import java.util.List;

public class CountDownContainer extends Container {
    private List<String> playernames;
    private int seconds;

    public CountDownContainer(int seconds) {
        super(GameState.GAME_STARTING);
        this.seconds = seconds;
    }

    public List<String> getPlayernames() {
        return playernames;
    }

    public void addPlayerName(String playername){
        playernames.add(playername);
    }
    public int getSeconds() {
        return seconds;
    }

    @Override
    public GameState getCurrentState() {
        return null;
    }
}
