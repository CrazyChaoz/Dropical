package at.dropical.server.game;

import at.dropical.server.gamestates.StartingState;
import at.dropical.server.gamestates.State;
import at.dropical.server.gamestates.WaitingState;
import at.dropical.shared.net.requests.HandleInputRequest;
import at.dropical.shared.net.transmitter.Transmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    //Zuseher & Spieler
    private List<Transmitter> observer = new ArrayList();

    //Games
    private List<OnePlayer> games = new ArrayList<>();

    //Level
    private int level = 0;

    //Time
    private Object time;    //TODO: Implement time

    //Current State the Game is in
    private State gameState = new WaitingState(this);

    //Maximal number of Players
    private int maxPlayers=2;

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
    public List<OnePlayer> getGames() {
        return games;
    }

    public int getLevel() {
        return level;
    }

//    public int getNumAI() {
//        return numAI;
//    }

    //Method


    public void addPlayer(String playerName, Transmitter transmitter) {
        if(games.size()<maxPlayers){
            games.add(new OnePlayer(playerName));
            observer.add(transmitter);
        }

        if(games.size()==maxPlayers)
            gameState=new StartingState(this);

    }

//    public int addAI(ServerTransmitter transmitter) {
//        int retval = addPlayer("Zufallsname: Rüdiger", transmitter);
//        if (retval != -1) {
//            numAI++;
//        }
//        return retval;
//    }

    public void addViewer(Transmitter transmitter) {
        observer.add(transmitter);
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public void handleInput(HandleInputRequest idc, int playerNumber) {
//        gameState.handleInput(idc, playerNumber);
    }

    public void updateClients() throws IOException {
//        gameState.fillGameDataContainer(gameDataContainer);
//
//        for (Transmitter player : players) {
//            player.writeRequest(gameDataContainer);
//        }
//        for (Transmitter viewer : viewers) {
//            viewer.writeRequest(gameDataContainer);
//        }
    }

}
