package at.dropical.shared.net.container;

import at.dropical.shared.GameState;
import at.dropical.shared.net.abstracts.Container;

import java.util.ArrayList;
import java.util.List;

public class GameOverContainer extends Container {
    private List<String> playernames=new ArrayList<>();
    private String looser;

    public GameOverContainer(String looser) {
        super(GameState.GAME_OVER);
        this.looser = looser;
    }


    public List<String> getPlayernames() {
        return playernames;
    }

    public void addPlayerName(String playername){
        playernames.add(playername);
    }

    public String getLooser() {
        return looser;
    }
}
