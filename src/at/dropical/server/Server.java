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

//  Constructor
    private Server() {
        new AccepterLoop(2345);
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
    private ListRequest listRequest(){
        ListRequest request=new ListRequest();

        return request;
    }

    private void joinRequest(JoinRequest joinRequest){

    }
}
