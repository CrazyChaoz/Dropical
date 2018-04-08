package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.InputDataContainer;

public class WaitingState implements State {
    @Override
    public GameDataContainer fillGameDataContainer(Game game, GameDataContainer gameDataContainer) {
        return null;
    }

    @Override
    public void handleInput(Game game, InputDataContainer inputDataContainer, int playerNumber) {

    }
}
