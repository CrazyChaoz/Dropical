package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.InputDataContainer;

public class RunningState implements State {

    @Override
    public GameDataContainer fillGameDataContainer(Game game, GameDataContainer gameDataContainer) {
        //static, ez
        gameDataContainer.getArenas()[0]=game.getGames()[0].getVisualArena();
        gameDataContainer.getArenas()[1]=game.getGames()[1].getVisualArena();

        gameDataContainer.getNextTrock()[0]=game.getGames()[0].getNextTrock();
        gameDataContainer.getNextTrock()[1]=game.getGames()[1].getNextTrock();

        gameDataContainer.getPlayernames()[0]=game.getGames()[0].getName();
        gameDataContainer.getPlayernames()[1]=game.getGames()[1].getName();

        gameDataContainer.setLevel(game.getLevel());

        return gameDataContainer;
    }

    @Override
    public void handleInput(Game game, InputDataContainer inputDataContainer) {

    }
}
