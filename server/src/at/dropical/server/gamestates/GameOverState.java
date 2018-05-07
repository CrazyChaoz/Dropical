package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.container.CountDownContainer;
import at.dropical.shared.net.container.GameOverContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

import java.util.Map;

public class GameOverState extends State {

    private String looser;

    public GameOverState(Game game) {
        super(game);
        game.interrupt();
    }

    public GameOverState(Game game,String looser) {
        super(game);
        this.looser=looser;
    }

    @Override
    public Container getContainer() {
        GameOverContainer container=new GameOverContainer(looser);
        for (Map.Entry<String,OnePlayer> onePlayer : game.getGames().entrySet()) {
            container.addPlayerName(onePlayer.getValue().getPlayername());
        }
        return container;
    }

    @Override
    public void handleInput(OnePlayer player, HandleInputRequest inputDataContainer) {

    }
}
