package at.dropical.server;

import at.dropical.server.logging.LoggerSetup;

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

    /** Fields of the instance. */
    private Logger logger;
    private GameManager manager;
    /** The pool is mainly used for the Receiver Threads. */
    private ExecutorService executor;

    /** Initialise and wait for connections. */
    public static void main(String[] args) {
        try {
            serverInstance = new Server();
            // Only returns on Error.
            serverInstance.manager.endlesslyAcceptConnections();

        } catch(IOException e) {
            log(Level.SEVERE, e.toString());
        } finally {
            instance().executor.shutdownNow();
        }
    }

    private Server() throws IOException {
        LoggerSetup.setup();
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        //TODO Deamon Threads Factory
        executor = Executors.newCachedThreadPool();

        manager = new GameManager(new ServerSocket(serverPort));
        new WebInterface(new ServerSocket(adminPort));
    }

    /*  Globally available functions of the Server. */
    public static Server instance() {
        return serverInstance;
    }

    /** Log in a file and sout.
     * fixme This is always used as the stack location in the message. Not useful. */
    public static void log(Level level, String msg) {
        instance().logger.log(level, msg);
    }

    /** Run some code concurrenty in the global Thread pool. */
    public static void execute(Runnable runnable) {
        instance().executor.execute(runnable);
    }

    public GameManager getManager() {
        return manager;
    }
}
