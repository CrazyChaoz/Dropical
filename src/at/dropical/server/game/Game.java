package at.dropical.server.game;

import at.dropical.server.Player;
import at.dropical.server.Viewer;
import at.dropical.server.gamestates.StartingState;
import at.dropical.server.gamestates.State;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.transmitter.Transmitter;

import java.util.ArrayList;
import java.util.List;

public class Game {
    //Zuseher
    private List<Viewer> viewers = new ArrayList();

    //Games
    private A_Single_Game[] games;

    //Level
    private int level = 0;

    //Time
    private Object time;    //TODO: Implement time

    private State gameState = new StartingState();
    private GameDataContainer gameDataContainer = new GameDataContainer();

    /**
     * <Constructors>
     **/

    //Classic
    public Game() {
        int playercount = 2;
        games = new A_Single_Game[playercount];
    }

    //Variable Players
    public Game(int playercount) {
        games = new A_Single_Game[playercount];
    }

    /**
     * <!Constructors>
     **/

    //Getter
    public A_Single_Game[] getGames() {
        return games;
    }

    public int getLevel() {
        return level;
    }

    //Method
    public void addPlayer(String playerName, Transmitter transmitter) {
        new A_Single_Game(new Player(transmitter, playerName));
    }

    public void addViewer(Transmitter transmitter) {
        viewers.add(new Viewer(transmitter));
    }

    public void updateClients() {

        gameState.fillGameDataContainer(this,gameDataContainer);

        for (A_Single_Game game : games) {
            game.getPlayer().getTransmitter().writeRequest(gameDataContainer);
        }
        for (Viewer viewer : viewers) {
            viewer.getTransmitter().writeRequest(gameDataContainer);
        }
    }
}
