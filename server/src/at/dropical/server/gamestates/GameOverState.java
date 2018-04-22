package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.requests.HandleInputRequest;

public class GameOverState extends State {

    private String looser;

    public GameOverState(Game game) {
        super(game);
    }

    public GameOverState(Game game,String looser) {
        super(game);
        this.looser=looser;
    }

    @Override
    public Container getContainer() {
        return null;
    }

    @Override
    public void handleInput(OnePlayer player, HandleInputRequest inputDataContainer) {

    }
}
