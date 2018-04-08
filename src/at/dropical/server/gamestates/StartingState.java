package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.shared.GameState;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.InputDataContainer;

public class StartingState extends Thread implements State {
    private int time;
    private Game game;

    public StartingState(Game game) {
        time=5;
        this.game=game;
        this.start();
    }

    public StartingState(Game game,int time) {
        this.time=time;
        this.game=game;
        this.start();
    }

    @Override
    public GameDataContainer fillGameDataContainer(Game game, GameDataContainer gameDataContainer) {
        gameDataContainer.setState(GameState.GAME_STARTING);
        gameDataContainer.setTime(time);
        return gameDataContainer;
    }

    @Override
    public void handleInput(Game game, InputDataContainer inputDataContainer, int playerNumber) {

    }

    @Override
    public void run() {
        try {
            for (; time <= 0; time--)
                Thread.sleep(1000);

            game.setGameState(new RunningState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
