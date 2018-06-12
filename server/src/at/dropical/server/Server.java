package at.dropical.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Here are main() and some functionality that
 * is globally managed, like
 * + execute() as a global thread pool
 * + log()
 */
public class Server {

    //The serverPort
    private static final int serverPort = 45000;
    private static final int adminPort = 45666;

    /** Singleton Code. Initialised in main(). */
    private static Server serverInstance;
    private static Logger logger;

    /** Fields of the instance. */
    private GameManager manager;
    /** The pool is mainly used for the Receiver Threads. */
    private ExecutorService executor;

    public static void main(String[] args) {
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        try {
            serverInstance = new Server();
            // Only returns on Error.
            serverInstance.manager.endlesslyAcceptConnections();

        } catch(IOException e) {
            log(Level.SEVERE, e.toString());
        }
    }

    private Server() throws IOException {
        //TODO Deamon Threads Factory
        executor = Executors.newCachedThreadPool();

        manager = new GameManager(new ServerSocket(serverPort));
        new WebInterface(new ServerSocket(adminPort));
    }

    /*  Globally available functions of the Server. */
    public static Server instance() {
        return serverInstance;
    }
    /** Log in a file and sout. */
    public static void log(Level level, String msg) {
        logger.log(level, msg);
    }

    /** Run some code concurrenty in the global Thread pool. */
    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }
}
