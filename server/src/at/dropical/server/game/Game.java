package at.dropical.server.game;

import at.dropical.server.Server;
import at.dropical.server.gamestates.GameOverState;
import at.dropical.server.gamestates.StartingState;
import at.dropical.server.gamestates.WaitingState;
import at.dropical.server.transmitter.ServerToClientAdapter;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.abstracts.Transmitter;
import at.dropical.shared.net.requests.HandleInputRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

public class Game implements Runnable {

    private List<ServerToClientAdapter> viewers = new ArrayList<>();
    private List<ServerToClientAdapter> players = new ArrayList<>();

    private Map<String,OnePlayer> games = new HashMap<>();
    private at.dropical.server.gamestates.State currentGameState = new WaitingState(this);
    /** When this amrtsount of players is reached, the game starts. */
    private int countPlayers;

    private ReentrantLock playerLock =new ReentrantLock();
    private boolean updateClientsNextTime = false;

    //Classic
    public Game() {
        countPlayers =2;
    }
    //Variable Players
    public Game(int playercount) {
        this.countPlayers =playercount;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            gameLoop();

            // Wait some time.
            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        updateClients();
    }

    /** Loops every 10ms.
     * First processes all inputs and
     * then updates the arenas and
     * sends packages back to the client. */
    private void gameLoop() {
        boolean doUpdate = false;

        // Process inputs
        for(ServerToClientAdapter player : players) {
            for(HandleInputRequest inputRequest : player.getInputQueue()) {
                handleInput(inputRequest);
            }
        }

        // Do game logic
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

        // Send out data containers
        if(doUpdate || updateClientsNextTime) {
            updateClientsNextTime = false;
            updateClients();
        }
    }

    public void addPlayer(String playerName, Transmitter transmitter) {
        playerLock.lock();

        if (games.size() < countPlayers) {
            Server.log(Level.INFO,"Player "+playerName+" added");

            players.add(new ServerToClientAdapter(transmitter));
            games.put(playerName,new OnePlayer(playerName));
        }

        if (games.size()== countPlayers)
            this.setCurrentGameState(new StartingState(this));

        playerLock.unlock();

        updateClients();
    }

    /** TODO be able to Join as a Viewer. */
    public void addViewer(ServerToClientAdapter transmitter) {
        playerLock.lock();
        viewers.add(transmitter);
        playerLock.unlock();
    }

    public void setCurrentGameState(at.dropical.server.gamestates.State currentGameState) {
        Server.log(Level.INFO,"State changed to "+currentGameState.getClass());
        this.currentGameState = currentGameState;

        updateClientsNextTime = true;
    }

    /** Don't call this from any RequestHandler Threads!
     * Only from gameLoop. */
    private void handleInput(HandleInputRequest handleInputRequest){
        for (Map.Entry<String,OnePlayer> game : games.entrySet()) {
            if(game.getValue().getPlayername().equals(handleInputRequest.getPlayername())) {
                currentGameState.handleInput(game.getValue(),handleInputRequest);
                updateClientsNextTime = true;

                return;
            }
        }
    }

    private void updateClients() {
        Container container = currentGameState.getContainer();

        if(!playerLock.tryLock())
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
        playerLock.unlock();
    }

    public Map<String, OnePlayer> getGames() {
        return games;
    }

    public at.dropical.server.gamestates.State getCurrentGameState() {
        return currentGameState;
    }

}
