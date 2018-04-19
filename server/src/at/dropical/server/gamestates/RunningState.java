package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.GameOverException;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.PlayerAction;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

/** The RunningState fills the GameDataContainer with
 * the arena and tetromino from one player.
 *
 * Ironically, this class is stateless. */
public class RunningState implements State {

    public RunningState(Game game) {
    }

    /** Set all the values in the gameDataContainer. */
    @Override
    public void fillGameDataContainer(OnePlayer player, GameDataContainer container) {
//        container.setState(at.dropical.shared.GameState.GAME_RUNNING);
//        container.setPlayername(player.getPlayername());
//        container.setArena(player.getVisualArena());
//        //TODO currentTetromino ?
//        container.setNextTetromino(player.getNextTetromino());
//        container.setLevel(0); //TODO Level
//        container.setTime(0); //TODO Time
    }

    @Override
    public void handleInput(OnePlayer player, HandleInputRequest inputDataContainer) {
        PlayerAction action = inputDataContainer.getInput();

        try {
            switch (action) {
                case NOKEY:
                    break;
                case LEFT:
                    player.moveLeft();
                    break;
                case RIGHT:
                    player.moveRight();
                    break;
                case UP:
                    player.rotateLeft();
                    break;

                case DOWN:
                    player.moveDown();
                    break;
                case DROP:
                    player.dropTetromino();
                    break;

                case START:
                    break; //TODO
                case PAUSE:
                    break; //TODO
                case QUIT:
                    break; //TODO
            }
        }catch (GameOverException goe){

        }
    }
}
