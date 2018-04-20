package at.dropical.server.game;

import at.dropical.server.gamestates.WaitingState;
import at.dropical.server.gamestates.StartingState;
import at.dropical.server.gamestates.State;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.InputDataContainer;
import at.dropical.server.LocalServerTransmitter;
import at.dropical.server.ServerSideTransmitter;

import java.util.ArrayList;
import java.util.List;

public class Game {

    //Zuseher
    private List<ServerSideTransmitter> viewers = new ArrayList();

    //Players
    private List<ServerSideTransmitter> players = new ArrayList<>();

    //Games
    private List<A_Single_Game> games = new ArrayList<>();

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
    public List<A_Single_Game> getGames() {
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
    public int addPlayer(String playerName, ServerSideTransmitter transmitter) {
        if (players.get(0) == null && games.get(0) == null) {
            players.add(transmitter);
            games.add(new A_Single_Game(playerName));
            return 0;
        } else if (players.get(1) == null && games.get(1) == null) {
            players.add(transmitter);
            games.add(new A_Single_Game(playerName));
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

    public void addViewer(ServerSideTransmitter transmitter) {
        viewers.add(transmitter);
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public void handleInput(InputDataContainer idc, int playerNumber) {
        gameState.handleInput(idc, playerNumber);
    }

    public void updateClients() {
        gameState.fillGameDataContainer(gameDataContainer);

        for (ServerSideTransmitter player : players) {
            player.writeRequest(gameDataContainer);
        }
        for (ServerSideTransmitter viewer : viewers) {
            viewer.writeRequest(gameDataContainer);
        }
    }

}
