package at.dropical.server.game;

import at.dropical.server.Server;
import at.dropical.server.gamestates.GameOverState;
import at.dropical.server.gamestates.StartingState;
import at.dropical.server.gamestates.WaitingState;
import at.dropical.server.transmitter.ServerToClientAdapter;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.requests.HandleInputRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

public class Game extends Thread {

    private List<ServerToClientAdapter> viewers = new ArrayList<>();
    private List<ServerToClientAdapter> players = new ArrayList<>();

    private Map<String,OnePlayer> games = new HashMap<>();
    private at.dropical.server.gamestates.State currentGameState = new WaitingState(this);
    private int maxPlayers;

    private ReentrantLock safetyLock=new ReentrantLock();
    private boolean updateClientsNextTime = false;

    //Classic
    public Game() {
        maxPlayers=2;
    }
    //Variable Players
    public Game(int playercount) {
        this.maxPlayers=playercount;
    }


    public void addPlayer(String playerName, ServerToClientAdapter transmitter) {
        safetyLock.lock();

        if (maxPlayers>games.size()) {
            Server.log(Level.INFO,"Player "+playerName+" added");
            players.add(transmitter);
            games.put(playerName,new OnePlayer(playerName));
        }

        if (games.size()==maxPlayers)
            this.setCurrentGameState(new StartingState(this));

        safetyLock.unlock();

        updateClients();
    }

    /** TODO be able to Join as a Viewer. */
    public void addViewer(ServerToClientAdapter transmitter) {
        safetyLock.lock();
        viewers.add(transmitter);
        safetyLock.unlock();
    }

    public void setCurrentGameState(at.dropical.server.gamestates.State currentGameState) {
        Server.log(Level.INFO,"State changed to "+currentGameState.getClass());
        this.currentGameState = currentGameState;

        updateClientsNextTime = true;
    }

    public void handleInput(HandleInputRequest handleInputRequest){
        for (Map.Entry<String,OnePlayer> game : games.entrySet()) {
            if(game.getValue().getPlayername().equals(handleInputRequest.getPlayername())) {
                currentGameState.handleInput(game.getValue(),handleInputRequest);
                updateClientsNextTime = true;

                return;
            }
        }
    }

    public void updateClients() {
        Container container = currentGameState.getContainer();

        if(!safetyLock.tryLock())
            return;
        for (ServerToClientAdapter player : players) {
            if(player.stillConnected())
                player.writeRequest(container);
            else {
                players.remove(player);
            }

        }
        for (ServerToClientAdapter viewer : viewers) {
            if(viewer.stillConnected())
                viewer.writeRequest(container);
            else
                viewers.remove(viewer);
        }
        safetyLock.unlock();
    }

    /** Loops every 10ms. */
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
                    setCurrentGameState(new GameOverState(this,e.getLooserName()));
                    Server.log(Level.INFO,"Player "+e.getLooserName()+" lost his game.");
                }
            }
            if(doUpdate || updateClientsNextTime) {
                updateClientsNextTime = false;
                updateClients();
            }

            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        updateClients();
    }

    public Map<String, OnePlayer> getGames() {
        return games;
    }

    public at.dropical.server.gamestates.State getCurrentGameState() {
        return currentGameState;
    }

}
