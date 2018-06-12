package at.dropical.server;
// Created by julian on 11.06.18.

import at.dropical.server.game.Game;
import at.dropical.server.transmitter.ObjectTransmitter;
import at.dropical.shared.net.abstracts.Transmitter;
import at.dropical.shared.net.requests.JoinRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

/**
 * This Class manages all the games.
 * It is your man when you want to join or
 * if you want information.
 *
 * Idea for tournament server:
 * Have a separate set of Request just to handle the game
 * creation and management. But for now
 * those are not needed.
 */
public class GameManager {

    private ServerSocket serverSocket;
    /** As long as you have put & get not more than once
     * in a function, we shouldn't need locking. */
    private ConcurrentHashMap<String, Game> gamesMap;

    GameManager(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.gamesMap = new ConcurrentHashMap<>();
    }

    /** Loops and manages new Client connections,
     * game creation, ...
     * Runs in the main Thread. */
    void endlesslyAcceptConnections() {
        try {
            for(; ; ) {
                Socket connection = serverSocket.accept();
                // Ignore when one connection is faulty.
                Server.execute(() -> waitForJoinRequestAndJoin(connection));
            }
        // The ServerSocket is died. No hope :'(
        } catch(IOException e) {
            Server.log(Level.SEVERE, "IOException, ServerSocket probably faulty.");
        }
    }

    private void waitForJoinRequestAndJoin(Socket connection) {
        try {
            Transmitter trans = new ObjectTransmitter(connection.getInputStream(), connection.getOutputStream(), serverSocket);
            JoinRequest request = (JoinRequest) trans.readRequest();

            // Create game or join existing
            joinGame(request.getGameID(), request.getPlayerName(), request.wantsToPlayAgainsAI(), trans);

        } catch(ClassCastException e) {
            Server.log(Level.WARNING, "ClassCastException -- class received is not a subclass of Request");
        } catch(IOException | RuntimeException e) {
            Server.log(Level.WARNING, e.getLocalizedMessage());
        }
    }

    /** Create game or join existing
     * @param gameID Join existing or new game. Null means autojoin.
     * @param playAgainsAI Make an AI connect too.
     * @param trans The game needs the connection. */
    private void joinGame(@Nullable String gameID, @NotNull String playerName, boolean playAgainsAI, Transmitter trans) throws IllegalArgumentException {
        Objects.requireNonNull(playerName);
        Game game;

        if(gameID != null && !gameID.equals(""))
            game = joinExistingGame(gameID, playerName, trans);
        else game = autoJoinOrCreateGame(playerName, trans);
        //if(playAgainsAI) //TODO
            //joinExistingGame(new AI);
    }

    private Game joinExistingGame(String gameID, String playerName, Transmitter trans) {
        Game game = gamesMap.get(gameID);
        game.addPlayerAndStart(playerName, trans);
        return game;
    }

    private Game autoJoinOrCreateGame(String playerName, Transmitter trans) {
        String name = playerName +"'s game "+ Math.random();
        // Lamda expression wants a final variable.
        final Game[] game = {null};

        // Search for a non-full game.
        gamesMap.forEachValue(Long.MAX_VALUE, (gameValue -> {
            if(gameValue.acceptsMorePlayers())
                game[0] = gameValue;
        }));
        // No waiting game found.
        if(game[0] == null) {
            game[0] = new Game();
            gamesMap.put(name, game[0]);
        }

        game[0].addPlayerAndStart(playerName, trans);
        Server.log(Level.INFO, "Game "+ name +" added");
        return game[0];
    }

    public void deleteGame(Game game) {
        gamesMap.remove(game.getName()).close();
    }

    /** Get a game by its ID or name. */
    public Game getGame(String gameID){
        return gamesMap.get(gameID);
    }
    /** Legacy support. Returns a copy of the map. */
    public Map<String, Game> getAllGames() {
        return new HashMap<>(gamesMap);
    }
}
