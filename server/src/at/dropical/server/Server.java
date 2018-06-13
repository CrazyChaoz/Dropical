package at.dropical.server;

import at.dropical.server.logging.LoggerSetup;
import at.dropical.wolliAI.AiMain;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
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
            logger().log(Level.SEVERE, e.toString());
        } finally {
            instance().executor.shutdownNow();
        }
    }

    /** Initialise most stuff. */
    private Server() throws IOException {
        LoggerSetup.setup();
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        /* Daemon Thread factory */
        executor = Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });

        manager = new GameManager(new ServerSocket(serverPort));
        new WebInterface(new ServerSocket(adminPort));
    }

    /*  Globally available functions of the Server. */
    public static Server instance() {
        return serverInstance;
    }

    /** I tried to have a static Method Server.log() before,
     * but then the stack trace always just shows that method. Not helpful. */
    public static Logger logger() {
        return instance().logger;
    }

    /** Run some code concurrenty in the global Thread pool. */
    public static void execute(Runnable runnable) {
        instance().executor.execute(runnable);
    }

    public GameManager getManager() {
        return manager;
    }


    /** It communicates over a socket instead of localTransmitter. */
    static void startLocalAI(String gameID) {
        execute(() -> {
            try {
                AiMain.newAIconnection(gameID);
            } catch(InterruptedException e) {
                logger().log(Level.INFO, "AI was interrupted.");
            }
        });
    }
}
