package at.dropical.server.game;

import at.dropical.server.Viewer;
import at.dropical.server.gamestates.StartingState;
import at.dropical.server.gamestates.State;
import at.dropical.server.gamestates.WaitingState;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.InputDataContainer;
import at.dropical.shared.net.transmitter.LocalServerTransmitter;
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

    private State gameState = new WaitingState(this);
    private GameDataContainer gameDataContainer = new GameDataContainer();

    //how many AI are connected
    private int numAI = 0;

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
    /*
    public Game(int playercount) {
        games = new A_Single_Game[playercount];
        players = new Viewer[playercount];
    }*/

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

    public int getNumAI() {
        return numAI;
    }

    //Method

    /**
     * @return -1 if no players can be added
     */
    public int addPlayer(String playerName, Transmitter transmitter) {
        if (players[0] == null && games[0] == null) {
            players[0] = new Viewer(transmitter);
            games[0] = new A_Single_Game(playerName);
            return 0;
        } else if (players[1] == null && games[1] == null) {
            players[1] = new Viewer(transmitter);
            games[1] = new A_Single_Game(playerName);
            gameState=new StartingState(this);
            return 1;
        }
        return -1;
    }

    public int addAI(LocalServerTransmitter transmitter) {
        int retval = addPlayer("Zufallsname: Rüdiger", transmitter);
        if (retval != -1) {
            numAI++;
        }
        return retval;
    }

    public void addViewer(Transmitter transmitter) {
        viewers.add(new Viewer(transmitter));
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public void handleInput(InputDataContainer idc, int playerNumber) {
        gameState.handleInput(idc, playerNumber);
    }

    public void updateClients() {
        gameState.fillGameDataContainer(gameDataContainer);

        for (Viewer player : players) {
            player.getTransmitter().writeRequest(gameDataContainer);
        }
        for (Viewer viewer : viewers) {
            viewer.getTransmitter().writeRequest(gameDataContainer);
        }
    }

}
