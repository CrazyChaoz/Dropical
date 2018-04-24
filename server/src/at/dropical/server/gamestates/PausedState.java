package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.GameState;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.container.GameDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

import java.util.Map;

public class PausedState extends State {

    public PausedState(Game game) {
        super(game);

        game.interrupt();
    }

    @Override
    public Container getContainer() {
        GameDataContainer gameDataContainer=new GameDataContainer(GameState.PAUSE);

        for (Map.Entry<String,OnePlayer> onePlayer : game.getGames().entrySet()) {
            gameDataContainer.addLevel(onePlayer.getValue().getLevel());
            gameDataContainer.addPlayerName(onePlayer.getKey());
            gameDataContainer.addArena(null);
            gameDataContainer.addCurrTrock(null);
            gameDataContainer.addNextTrock(null);
            gameDataContainer.addCurrTrockX(null);
            gameDataContainer.addCurrTrockY(null);
        }
        return gameDataContainer;
    }

    @Override
    public void handleInput(OnePlayer player, HandleInputRequest inputDataContainer) {
        switch (inputDataContainer.getInput()){
            case PAUSE:
                game.setCurrentGameState(new RunningState(game));
                break;
            case QUIT:

                break;
        }
    }
}
