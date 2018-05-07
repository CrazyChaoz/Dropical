package at.dropical.server;

/**
 * The Top Level Class
 * Callable with Server.instance();
 * */

import at.dropical.server.game.Game;
import at.dropical.server.logging.LoggerSetup;
import at.dropical.server.transmitter.LocalServerTransmitter;
import at.dropical.shared.LocalRequestCache;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server {
    public static void main(String[] args){
        Server.instance();
    }

    //Singleton code
    //NO TOUCHY-TOUCHY
    private static Server privateInstance = new Server();
    public static Server instance() {
        return privateInstance;
    }


/**
 * The Server starts here
 */

//  STATICS

public static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    //spamprotection
    public static final boolean isPureAiGameAllowed=true;
    //human tournaments
    public static final boolean isAiAllowed=true;

    //The port 
    private static final int port=45000;
    
    //
    private static final boolean isTounamentServer=true;

    public static ExecutorService serverExecutor=Executors.newCachedThreadPool();

//  Constructor

    private Server() {
        try {
            LoggerSetup.setup();
            new RemoteAccepterLoop(new ServerSocket(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//  Declarations
    private Map<String,Game> games=new HashMap<>();


//  Getter
    public Map<String, Game> getAllGames() {
        return games;
    }

    public Game getGame(String gameID){
        return games.get(gameID);
    }

//  Setter

//  Methods

    public void addLocalClient(LocalRequestCache requestCache) {
        LocalServerTransmitter localServerTransmitter=new LocalServerTransmitter(requestCache);
        serverExecutor.execute(()->{
            try {
                new Loop(localServerTransmitter);
            } catch (IOException|ClassNotFoundException e) {}
        });
    }
}
