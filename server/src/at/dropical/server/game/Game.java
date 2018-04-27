package at.dropical.server.game;

import at.dropical.server.Server;
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
import java.util.logging.Level;

public class Game extends Thread{

    //Zuseher
    private List<ServerSideTransmitter> viewers = new ArrayList();

    //Players
    private List<ServerSideTransmitter> players = new ArrayList<>();

    //Games
    private Map<String,OnePlayer> games = new HashMap<>();


    private at.dropical.server.gamestates.State currentGameState = new WaitingState(this);

    //maximum number of players
    private int maxPlayers;


    //Classic
    public Game() {
        maxPlayers=2;
    }
    //Variable Players
    public Game(int playercount) {
        this.maxPlayers=playercount;
    }




    public Map<String, OnePlayer> getGames() {
        return games;
    }

    public at.dropical.server.gamestates.State getCurrentGameState() {
        return currentGameState;
    }



    /**
     * @return -1 if no players can be added
     */
    public void addPlayer(String playerName, ServerSideTransmitter transmitter) {
        if (maxPlayers>games.size()) {
            Server.LOGGER.log(Level.INFO,"Player "+playerName+" added");
            players.add(transmitter);
            games.put(playerName,new OnePlayer(playerName));
            updateClients();
        }

        if (games.size()==maxPlayers)
            this.setCurrentGameState(new StartingState(this));
    }

    public void addViewer(ServerSideTransmitter transmitter) {
        viewers.add(transmitter);
    }

    public void setCurrentGameState(at.dropical.server.gamestates.State currentGameState) {
        Server.LOGGER.log(Level.INFO,"State changed to "+currentGameState.getClass());
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
        Container container = currentGameState.getContainer();

        for (ServerSideTransmitter player : players) {
            player.writeRequest(container);
        }
        for (ServerSideTransmitter viewer : viewers) {
            viewer.writeRequest(container);
        }
    }

    @Override
    public void run() {
        boolean doUpdate;
        while (!isInterrupted()) {
            doUpdate=false;

            for (Map.Entry<String, OnePlayer> gameEntry : games.entrySet()) {
                try {
                    if (gameEntry.getValue().update()) {
                        doUpdate = true;
                    }
                } catch (GameOverException e) {
                    Server.LOGGER.log(Level.INFO,"Player "+e.getLooserName()+" lost his game.");
                } catch(LinesClearedException e) {
                    Server.LOGGER.log(Level.INFO,e.getLines() +" lines cleared");
                }
            }
            if(doUpdate)
                updateClients();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
        }
    }
}
