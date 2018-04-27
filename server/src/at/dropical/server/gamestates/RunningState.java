package at.dropical.server.gamestates;

import at.dropical.server.Server;
import at.dropical.server.game.Game;
import at.dropical.server.game.GameOverException;
import at.dropical.server.game.LinesClearedException;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.GameState;
import at.dropical.shared.PlayerAction;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.container.GameDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

import java.util.Map;
import java.util.logging.Level;

/** The RunningState fills the GameDataContainer with
 * the arena and tetromino from one player.
 *
 * Ironically, this class is stateless. */
public class RunningState extends State {

    public RunningState(Game game) {
        super(game);

        game.start();
    }


    /** Set all the values in the gameDataContainer. */
    @Override
    public Container getContainer() {
        GameDataContainer container=new GameDataContainer(GameState.RUNNING);

        for (Map.Entry<String,OnePlayer> onePlayer : game.getGames().entrySet()) {
            container.addLevel(onePlayer.getValue().getLevel());
            container.addPlayerName(onePlayer.getValue().getPlayername());
            container.addArena(onePlayer.getValue().getArena());
            container.addCurrTrock(onePlayer.getValue().getCurrTetromino().toArray());
            container.addNextTrock(onePlayer.getValue().getNextTetromino().toArray());
            container.addCurrTrockX(onePlayer.getValue().getCurrTetrX());
            container.addCurrTrockY(onePlayer.getValue().getCurrTetrY());
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
                    player.rotateRight();
                    break;
                case DOWN:
                    player.moveDown();
                    break;
                case DROP:
                    player.dropTetromino();
                    break;
                case START: //TODO
                    break;
                case PAUSE:
                    game.setCurrentGameState(new PausedState(game));
                    break;
                case QUIT: //TODO
                    game.setCurrentGameState(new GameOverState(game));
                    break;
            }
        }catch (GameOverException goe){
            //TODO Game Over
            Server.LOGGER.log(Level.INFO,"Game over for player "+ player.getPlayername());
        } catch(LinesClearedException e) {
            // TODO Add lines to others
            Server.LOGGER.log(Level.INFO,e.getLines() +" lines cleared");
        }
    }
}
