package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.InputDataContainer;

public abstract class State{
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public abstract GameDataContainer fillGameDataContainer(GameDataContainer gameDataContainer);
    public abstract void handleInput(InputDataContainer inputDataContainer,int playerNumber);
}

