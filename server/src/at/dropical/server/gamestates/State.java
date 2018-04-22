package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.requests.HandleInputRequest;

public abstract class State {
    protected Game game;


    public State(Game game) {
        this.game = game;
    }

    public abstract Container getContainer();
    public abstract void handleInput(OnePlayer player, HandleInputRequest inputDataContainer);
}

