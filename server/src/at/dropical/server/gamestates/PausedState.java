package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.GameState;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

public class PausedState implements State {

    public PausedState(Game game) {
        super(game);
    }

    @Override
    public void fillGameDataContainer(OnePlayer player, GameDataContainer gameDataContainer) {

//        gameDataContainer.getPlayernames()[0]=game.getGames().get(0).getName();
//        gameDataContainer.getPlayernames()[1]=game.getGames().get(1).getName();
//
//        gameDataContainer.setState(GameState.GAME_PAUSE);
//
//        gameDataContainer.setLevel(game.getLevel());
    }

    @Override
    public void handleInput(OnePlayer player, HandleInputRequest inputDataContainer) {

    }
}
