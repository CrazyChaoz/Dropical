package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.shared.GameState;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.InputDataContainer;

public class PausedState implements State {
    @Override
    public GameDataContainer fillGameDataContainer(Game game, GameDataContainer gameDataContainer) {

        gameDataContainer.getPlayernames()[0]=game.getGames()[0].getName();
        gameDataContainer.getPlayernames()[1]=game.getGames()[1].getName();

        gameDataContainer.setState(GameState.GAME_PAUSE);

        gameDataContainer.setLevel(game.getLevel());

        return gameDataContainer;
    }

    @Override
    public void handleInput(Game game, InputDataContainer inputDataContainer, int playerNumber) {

    }
}
