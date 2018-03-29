package at.dropical.server.game;

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

    //Players
    private Viewer[] players;

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
        players = new Viewer[playercount];
    }

    //Variable Players
    public Game(int playercount) {
        games = new A_Single_Game[playercount];
        players = new Viewer[playercount];
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

    /**@return false if no players can be added*/
    public boolean addPlayer(String playerName, Transmitter transmitter) {
        if(players[0]==null&&games[0]==null){
            players[0]=new Viewer(transmitter);
            games[0]=new A_Single_Game(playerName);
            return true;
        }else if(players[1]==null&&games[1]==null){
            players[1]=new Viewer(transmitter);
            games[1]=new A_Single_Game(playerName);
            return true;
        }
        return false;
    }

    public void addViewer(Transmitter transmitter) {
        viewers.add(new Viewer(transmitter));
    }

    public void updateClients() {

        gameState.fillGameDataContainer(this,gameDataContainer);

        for (Viewer viewer : viewers) {
            viewer.getTransmitter().writeRequest(gameDataContainer);
        }
    }
}
