package at.dropical.server.game;

import at.dropical.server.gamestates.StartingState;
import at.dropical.server.gamestates.State;
import at.dropical.server.gamestates.WaitingState;
import at.dropical.server.transmitter.ServerSideTransmitter;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.requests.HandleInputRequest;

import java.util.ArrayList;
import java.util.List;

public class Game {

    //Zuseher
    private List<ServerSideTransmitter> viewers = new ArrayList();

    //Players
    private List<ServerSideTransmitter> players = new ArrayList<>();

    //Games
    private List<OnePlayer> games = new ArrayList<>();


    private at.dropical.server.gamestates.State currentGameState = new WaitingState(this);

    //maximum number of players
    private int maxPlayers = 0;


    //Classic
    public Game() {
    }
    //Variable Players
    public Game(int playercount) {
        this.maxPlayers=playercount;
    }




    //Getter
    public List<OnePlayer> getGames() {
        return games;
    }


    //Method

    /**
     * @return -1 if no players can be added
     */
    public void addPlayer(String playerName, ServerSideTransmitter transmitter) {
        if (maxPlayers<games.size()) {
            players.add(transmitter);
            games.add(new OnePlayer(playerName));
        }

        if (games.size()==maxPlayers)
            this.setCurrentGameState(new StartingState(this));
    }

    public void addViewer(ServerSideTransmitter transmitter) {
        viewers.add(transmitter);
    }

    public void setCurrentGameState(at.dropical.server.gamestates.State currentGameState) {
        this.currentGameState = currentGameState;
    }

    public void handleInput(HandleInputRequest handleInputRequest){
        for (OnePlayer game : games) {
            if(game.getPlayername().equals(handleInputRequest.getPlayername())) {
                currentGameState.handleInput(game,handleInputRequest);
                return;
            }
        }
    }

    public void updateClients() {
        Container container= currentGameState.getContainer();

        for (ServerSideTransmitter player : players) {
            player.writeRequest(container);
        }
        for (ServerSideTransmitter viewer : viewers) {
            viewer.writeRequest(container);
        }
    }
}
