package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.GameState;
import at.dropical.shared.net.requests.Container;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

public class PausedState extends State {

    public PausedState(Game game) {
        super(game);
    }

    @Override
    public Container getContainer() {
        GameDataContainer gameDataContainer=new GameDataContainer(GameState.GAME_PAUSE);
        gameDataContainer.setLevel(game.getLevel());

        for (OnePlayer onePlayer : game.getGames()) {
            gameDataContainer.addPlayerName(onePlayer.getPlayername());
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
