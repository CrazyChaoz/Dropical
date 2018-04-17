package at.dropical.server.game;

import at.dropical.server.gamestates.WaitingState;
import at.dropical.server.gamestates.StartingState;
import at.dropical.server.gamestates.State;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.InputDataContainer;
import at.dropical.shared.net.transmitter.LocalServerTransmitter;
import at.dropical.shared.net.transmitter.Transmitter;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a "play round" with 1, 2 or many players.
 *
 * It manages the Connections (transmitters, viewers) and
 * the {@link A_Single_Game}s.
 */
public class Game {

    //Zuseher
    private List<Transmitter> viewers = new ArrayList();

    //Players
    private List<Transmitter> players = new ArrayList<>();

    //Games
    private List<A_Single_Game> games = new ArrayList<>();

    private int level = 0;
    //TODO Time

    private State gameState = new WaitingState(this);
    private GameDataContainer gameDataContainer = new GameDataContainer();


    // TODO
    public void updateClients() {

    }

}
