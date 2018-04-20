package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.shared.GameState;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.InputDataContainer;

public class RunningState extends State {

    public RunningState(Game game) {
        super(game);
    }

    @Override
    public GameDataContainer fillGameDataContainer(GameDataContainer gameDataContainer) {

        //static, ez
        gameDataContainer.getArenas()[0]=game.getGames().get(0).getVisualArena();
        gameDataContainer.getArenas()[1]=game.getGames().get(1).getVisualArena();

        gameDataContainer.getNextTrock()[0]=game.getGames().get(0).getNextTetromino();
        gameDataContainer.getNextTrock()[1]=game.getGames().get(1).getNextTetromino();

        gameDataContainer.getPlayernames()[0]=game.getGames().get(0).getName();
        gameDataContainer.getPlayernames()[1]=game.getGames().get(1).getName();

        gameDataContainer.setState(GameState.GAME_RUNNING);

        gameDataContainer.setLevel(game.getLevel());

        return gameDataContainer;
    }

    @Override
    public void handleInput(InputDataContainer inputDataContainer,int playerNumber) {
        //TODO: handle input, duh
    }
}
