package at.dropical.server.game;

import at.dropical.server.gamestates.StartingState;
import at.dropical.server.gamestates.State;
import at.dropical.server.gamestates.WaitingState;
import at.dropical.server.transmitter.ServerSideTransmitter;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.requests.HandleInputRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game extends Thread{

    //Zuseher
    private List<ServerSideTransmitter> viewers = new ArrayList();

    //Players
    private List<ServerSideTransmitter> players = new ArrayList<>();

    //Games
    private Map<String,OnePlayer> games = new HashMap<>();


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

    public Map<String, OnePlayer> getGames() {
        return games;
    }


    //Method

    /**
     * @return -1 if no players can be added
     */
    public void addPlayer(String playerName, ServerSideTransmitter transmitter) {
        if (maxPlayers<games.size()) {
            players.add(transmitter);
            games.put(playerName,new OnePlayer(playerName));
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
        for (Map.Entry<String,OnePlayer> game : games.entrySet()) {
            if(game.getValue().getPlayername().equals(handleInputRequest.getPlayername())) {
                currentGameState.handleInput(game.getValue(),handleInputRequest);
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

    @Override
    public void run() {
        while (!isInterrupted()) {
            boolean doUpdate = false;

            for (Map.Entry<String, OnePlayer> game : games.entrySet()) {
                try {
                    if (game.getValue().update()) {
                        doUpdate = true;
                    }
                } catch (GameOverException e) {
                    e.getLooserName();
                }


            }
        }
    }
}
