package at.dropical.server.game;

import at.dropical.server.Server;
import at.dropical.server.gamestates.GameOverState;
import at.dropical.server.gamestates.StartingState;
import at.dropical.server.gamestates.WaitingState;
import at.dropical.server.transmitter.ServerToClientAdapter;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.abstracts.Transmitter;
import at.dropical.shared.net.requests.HandleInputRequest;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

public class Game extends Thread implements AutoCloseable {

    private List<ServerToClientAdapter> viewers = new ArrayList<>();
    private List<ServerToClientAdapter> players = new ArrayList<>();

    private Map<String,OnePlayer> games = new HashMap<>();
    private at.dropical.server.gamestates.State currentGameState = new WaitingState(this);
    /** When this amount of players is reached, the game starts. */
    private int necessaryPlayers;

    private ReentrantLock playerLock =new ReentrantLock();
    private boolean updateClientsNextTime = false;

    //Classic
    public Game(String name) {
        setName(name);
        necessaryPlayers =2;
    }
    //Variable Players
    public Game(int playercount, String name) {
        setName(name);
        this.necessaryPlayers =playercount;
    }

    /** TODO game doesn't run in the Server executor. */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            gameLoop();

            // Wait some time.
            try {
                Thread.sleep(10);
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
                Server.logger().log(Level.INFO, "Game was terminated");
            }
        }
        updateClients();
    }

    /** Loops every 10ms.
     * First processes all inputs and
     * then updates the arenas and
     * sends packages back to the client.
     *
     * Apparently this is not the only place where logic
     * happens. In the gamestates there is a bunch
     * of logic too :( */
    private void gameLoop() {
        boolean doUpdate = false;

        // Process inputs
        for(ServerToClientAdapter player : players) {
            HandleInputRequest request = player.pollInput();
            while(request != null) {
                handleInput(request);
                request = player.pollInput();
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
                Server.logger().log(Level.INFO, "Player "+e.getLooserName()+" lost his game.");
            }
        }

        // Send out data containers
        if(doUpdate || updateClientsNextTime) {
            updateClientsNextTime = false;
            updateClients();
        }
    }

    /** The game starts when enough players entered. */
    public void addPlayerAndStart(String playerName, Transmitter transmitter) {
        playerLock.lock();

        if (games.size() < necessaryPlayers) {
            Server.logger().log(Level.INFO, "Player "+playerName+" added");

            players.add(new ServerToClientAdapter(transmitter));
            games.put(playerName,new OnePlayer(playerName));
        }

        if (games.size()== necessaryPlayers)
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
        Server.logger().log(Level.INFO, "State changed to "+currentGameState.getClass());
        this.currentGameState = currentGameState;

        updateClientsNextTime = true;
    }

    /** Don't call this from any RequestHandler Threads!
     * Only from gameLoop. */
    private void handleInput(HandleInputRequest inputRequest){
        for (Map.Entry<String,OnePlayer> gameEntry : games.entrySet()) {
            if(gameEntry.getValue().getPlayername().equals(inputRequest.getPlayername())) {
                currentGameState.handleInput(gameEntry.getValue(),inputRequest);
                updateClientsNextTime = true;

                return;
            }
        }
    }

    /** Send game data containers. */
    public void updateClients() {
        Container container = currentGameState.getContainer();

        if(!playerLock.tryLock())
            return;
        for(Iterator<ServerToClientAdapter> iterator = players.iterator(); iterator.hasNext(); ) {
            ServerToClientAdapter player = iterator.next();
            if(player.stillConnected())
                player.writeRequest(container);
            else {
                iterator.remove();
            }

        }
        for(Iterator<ServerToClientAdapter> iterator = viewers.iterator(); iterator.hasNext(); ) {
            ServerToClientAdapter viewer = iterator.next();
            if(viewer.stillConnected())
                viewer.writeRequest(container);
            else
                iterator.remove();
        }
        playerLock.unlock();
    }

    /** End all connections and terminate Threads. */
    public void close() {
        this.interrupt();
        playerLock.lock();
        try {
            for(ServerToClientAdapter player : players) {
                player.close();
            }
            for(ServerToClientAdapter viewer : viewers) {
                viewer.close();
            }
        } catch(Exception ignored) { }
        playerLock.unlock();
    }

    public Map<String, OnePlayer> getGames() {
        return games;
    }

    public at.dropical.server.gamestates.State getCurrentGameState() {
        return currentGameState;
    }

    public boolean acceptsMorePlayers() {
        return getCurrentGameState() instanceof WaitingState;
    }
}
