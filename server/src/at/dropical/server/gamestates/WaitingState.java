package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.requests.HandleInputRequest;

/**
 * People are sent here while waiting on their game to start
 */
public class WaitingState extends State {

    public WaitingState(Game game) {
        super(game);
    }

    @Override
    public Container getContainer() {return null;}
    @Override
    public void handleInput(OnePlayer player, HandleInputRequest inputDataContainer) {}

}
