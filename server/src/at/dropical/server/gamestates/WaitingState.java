package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.GameState;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.container.ListDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

import java.util.Map;

/**
 * People are sent here while waiting on their game to start
 */
public class WaitingState extends State {

    public WaitingState(Game game) {
        super(game);
    }

    @Override
    public Container getContainer() {
        ListDataContainer container=new ListDataContainer(GameState.LOBBY);
        for (Map.Entry<String,OnePlayer> onePlayer : game.getGames().entrySet()) {
            container.addName(onePlayer.getValue().getPlayername());
        }
        return container;
    }

    @Override
    public void handleInput(OnePlayer player, HandleInputRequest inputDataContainer) {}

}
