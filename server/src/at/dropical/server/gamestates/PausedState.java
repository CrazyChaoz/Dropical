package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.shared.GameState;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

public class PausedState extends State {

    public PausedState(Game game) {
        super(game);
    }

    @Override
    public GameDataContainer fillGameDataContainer(GameDataContainer gameDataContainer) {

        gameDataContainer.getPlayernames()[0]=game.getGames().get(0).getName();
        gameDataContainer.getPlayernames()[1]=game.getGames().get(1).getName();

        gameDataContainer.setState(GameState.GAME_PAUSE);

        gameDataContainer.setLevel(game.getLevel());

        return gameDataContainer;
    }

    @Override
    public void handleInput(HandleInputRequest handleInputRequest, int playerNumber) {

    }
}
