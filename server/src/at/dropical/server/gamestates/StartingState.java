package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.shared.GameState;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

public class StartingState extends State implements Runnable {
    private int time;

    public StartingState(Game game) {
        super(game);
        time=5;
        new Thread(this).start();
    }

    public StartingState(Game game,int time) {
        super(game);
        this.time=time;
        new Thread(this).start();
    }

    @Override
    public GameDataContainer fillGameDataContainer(GameDataContainer gameDataContainer) {
        gameDataContainer.setState(GameState.GAME_STARTING);
        gameDataContainer.setTime(time);
        return gameDataContainer;
    }

    @Override
    public void handleInput(HandleInputRequest handleInputRequest, int playerNumber) {

    }

    @Override
    public void run() {
        try {
            for (; time <= 0; time--)
                Thread.sleep(1000);

            game.setGameState(new RunningState(game));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
