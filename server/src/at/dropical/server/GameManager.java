package at.dropical.server;
// Created by julian on 11.06.18.

import at.dropical.server.game.Game;
import at.dropical.server.transmitter.ObjectTransmitter;
import at.dropical.shared.net.abstracts.Transmitter;
import at.dropical.shared.net.requests.JoinRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        try(InputStream inputStream = connection.getInputStream();
            OutputStream outputStream = connection.getOutputStream()) {

            Transmitter trans = new ObjectTransmitter(inputStream, outputStream);
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
        else game = createAndJoinGame(playerName, trans);

        //if(playAgainsAI) //TODO
            //joinExistingGame(new AI);
    }

    private Game joinExistingGame(String gameID, String playerName, Transmitter trans) {
        Game game = gamesMap.get(gameID);
        game.addPlayer(playerName, trans);
        return game;
    }

    private Game createAndJoinGame(String playerName, Transmitter trans) {
        String name = playerName +"'s game "+ Math.random();
        Game game = new Game();

        gamesMap.put(name, game);
        game.addPlayer(playerName, trans);
        return game;
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
