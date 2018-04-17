package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

public abstract class State{
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public abstract GameDataContainer fillGameDataContainer(GameDataContainer gameDataContainer);
    public abstract void handleInput(HandleInputRequest handleInputRequest, int playerNumber);
}

