package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.shared.GameState;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.InputDataContainer;

public class RunningState extends State {

    public RunningState(Game game) {
        super(game);
    }

    /** Writes the arena, tetromino, ... of all games into
     * one container. */
    @Override
    public GameDataContainer fillGameDataContainer(GameDataContainer gameDataContainer) {

        for(int i = 0; i < game.getGames().size(); i++) {
            gameDataContainer.getArenas()[i] = game.getGames().get(i).getVisualArena();
            gameDataContainer.getNextTrock()[i] = game.getGames().get(i).getNextTetromino();
            gameDataContainer.getPlayernames()[i] = game.getGames().get(i).getName();
        }
        gameDataContainer.setState(GameState.GAME_RUNNING);
        gameDataContainer.setLevel(game.getLevel());

        return gameDataContainer;
    }

    @Override
    public void handleInput(InputDataContainer inputDataContainer,int playerNumber) {
        //TODO: handle input, duh
    }
}
