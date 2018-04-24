package at.dropical.server.gamestates;

import at.dropical.server.game.Game;
import at.dropical.server.game.OnePlayer;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.container.CountDownContainer;
import at.dropical.shared.net.requests.HandleInputRequest;

import java.util.Map;

public class StartingState extends State implements Runnable {
    private int time;
    private Game game;

    public StartingState(Game game) {
        super(game);
        this.game=game;
        time=15;
        new Thread(this).start();
    }

    public StartingState(Game game,int time) {
        super(game);
        this.game=game;
        this.time=time;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            for (; time >= 0; time--)
                Thread.sleep(1000);
            game.setCurrentGameState(new RunningState(game));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Container getContainer() {
        CountDownContainer container=new CountDownContainer(time);
        for (Map.Entry<String,OnePlayer> onePlayer : game.getGames().entrySet()) {
            container.addPlayerName(onePlayer.getValue().getPlayername());
        }
        return container;
    }

    @Override
    public void handleInput(OnePlayer player, HandleInputRequest inputDataContainer) {

    }
}
