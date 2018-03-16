import java.util.HashMap;
import java.util.Map;

public class Server {

    //Singleton code
    //NO TOUCHY-TOUCHY
    private static Server privateInstance = new Server();
    public static Server instance() {
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


//  Getter
    public Map<String, Game> getGames() {
        return games;
    }

//  Setter

}
