package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.GameState;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

public class StartingState implements Runnable, State {
    private int time;
    private Game game;

    public StartingState(Game game) {
        this.game=game;
        time=5;
        new Thread(this).start();
    }

    public StartingState(Game game,int time) {
        this.game=game;
        this.time=time;
        new Thread(this).start();
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

    @Override
    public void fillGameDataContainer(OnePlayer player, GameDataContainer gameDataContainer) {
//        gameDataContainer.setState(GameState.GAME_STARTING);
        gameDataContainer.setTime(time);
    }

    @Override
    public void handleInput(OnePlayer player, HandleInputRequest inputDataContainer) {

    }
}
