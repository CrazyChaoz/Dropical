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
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

/**
 * This Class manages all the games.
 * It is your man when you want to join or
 * if you want information.
 */
public class GameManager {

    private ServerSocket serverSocket;
    private ConcurrentHashMap<String, Game> gamesMap; //TODO

    public GameManager(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.gamesMap = new ConcurrentHashMap<>();
    }

    /** Loops and manages new Client connections,
     * game creation, ...
     * Runs in the main Thread. */
    public void endlesslyAcceptConnections() {
        try {
            for(; ; ) {
                Socket connection = serverSocket.accept();
                // Ignore when one connection is faulty.
                Server.instance().execute(() -> waitForJoinRequestAndJoin(connection));
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
            joinGame(request.getGameID(), request.getPlayerName(), request.wantsToPlayAgainsAI());

        } catch(ClassCastException e) {
            Server.log(Level.WARNING, "ClassCastException -- class received is not a subclass of Request");
        } catch(IOException e) {
            Server.log(Level.WARNING, e.getLocalizedMessage());
        }
    }

    /** Create game or join existing
     * @param gameID Join existing or new game. Null means autojoin.
     * @param playAgainsAI Make a new game with an AI.
     */
    private void joinGame(@Nullable String gameID, @NotNull String playerName, boolean playAgainsAI) {
        //TODO
    }

    /** Get a game by its ID or name. */
    public Game getGame(String gameID){
        return gamesMap.get(gameID);
    }
    /** Legacy support. Returns a copy of the map. */
    @Deprecated
    public Map<String, Game> getAllGames() {
        return new HashMap<>(gamesMap);
    }
}
