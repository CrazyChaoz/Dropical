package at.dropical.server.game;

import at.dropical.server.gamestates.StartingState;
import at.dropical.server.gamestates.WaitingState;
import at.dropical.server.gamestates.State;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.transmitter.Transmitter;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a "play round" with 1, 2 or many players.
 *
 * It manages the Connections (transmitters, viewers) and
 * the {@link OnePlayer}s.
 */
public class Game {

    int maxPlayers = 2;

    //Players
    private List<Transmitter> playerTransmitters = new ArrayList<>();

    //Games
    private List<OnePlayer> players = new ArrayList<>();

    private int level = 0;
    //TODO Time

    private State gameState = new WaitingState(this);

    public void addPlayer(String playerName, Transmitter transmitter) {
        if (players.size()<maxPlayers) {
            playerTransmitters.add(transmitter);
            players.add(new OnePlayer(playerName));
        } //TODO throw Exception when max players reached

        if (players.size()==maxPlayers)
            gameState=new StartingState(this);
    }

    /** Calls all the transmitters to send the Responses. */
    public void updateClients() {
        for(OnePlayer player : players) {
            gameState.fillGameDataContainer();
            player.getPlayerTransmitter().writeRequest(

            );
        }
    }

}
