package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.GameOverException;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.GameState;
import at.dropical.shared.PlayerAction;
import at.dropical.shared.net.requests.Container;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

/** The RunningState fills the GameDataContainer with
 * the arena and tetromino from one player.
 *
 * Ironically, this class is stateless. */
public class RunningState extends State {

    public RunningState(Game game) {
        super(game);
    }


    /** Set all the values in the gameDataContainer. */
    @Override
    public Container getContainer() {
        GameDataContainer container=new GameDataContainer(GameState.GAME_RUNNING);
        container.setLevel(game.getLevel());

        for (OnePlayer onePlayer : game.getGames()) {
            container.addPlayerName(onePlayer.getPlayername());
            container.addArena(onePlayer.getArena());
            container.addCurrTrock(onePlayer.getCurrTetromino().toArray());
            container.addNextTrock(onePlayer.getNextTetromino().toArray());
            container.addCurrTrockX(onePlayer.getCurrTetrX());
            container.addCurrTrockY(onePlayer.getCurrTetrY());
        }
        return container;
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
                    game.setCurrentGameState(new PausedState(game));
                    break;
                case QUIT:
                    game.setCurrentGameState(new GameOverState(game));
                    break; //TODO
            }
        }catch (GameOverException goe){

        }
    }
}
