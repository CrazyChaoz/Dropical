package at.dropical.server;

/**
 * The Top Level Class
 * Callable with Server.exe();
 * */

import at.dropical.server.game.Game;
import at.dropical.shared.net.requests.JoinRequest;
import at.dropical.shared.net.requests.ListRequest;
import at.dropical.shared.net.transmitter.Transmitter;


import java.util.*;

public class Server {


    //Singleton code
    //NO TOUCHY-TOUCHY
    private static Server privateInstance = new Server();
    public static Server exe() {
        return privateInstance;
    }


/**
 * The Server starts here
 */

//  STATICS

    //spamprotection
    public static final boolean isPureAiGameAllowed=true;
    //human tournaments
    public static final boolean isAiAllowed=true;

    //The port 
    private static final int port=2345;

//  Constructor
    private Server() {
        new AccepterLoop(port);
    }


//  Declarations
    private Map<String,Game> games=new HashMap<>();
    private List<Transmitter> connected=new ArrayList<>();


//  Getter
    public Map<String, Game> getAllGames() {
        return games;
    }

    public Game getGame(String gameID){
        return games.get(gameID);
    }

    public List<Transmitter> getConnected() {
        return connected;
    }
//  Setter

//  Methods
}
