package at.dropical.server.logging;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

/**
 * fixme How do I use this Logger ?
 * TODO Replace "System.err.println" with the logger.
 *
 */
public class LoggerSetup {
    static private FileHandler fileTxt;
    static private SimpleFormatter formatterTxt;

    static private FileHandler fileHTML;
    static private Formatter formatterHTML;

    static public void setup() throws IOException {
        System.out.println("Logger is in setup");

        // get the global logger to configure it
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        // suppress the logging output to the console
        /*Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();

        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }*/

        logger.setLevel(Level.INFO);
        // Make folder if it doesn't exist.
        new File("log").mkdir();
        fileTxt = new FileHandler("log/Logging.txt");
        fileHTML = new FileHandler("log/Logging.html");

        // create a TXT formatter
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);

        // create an HTML formatter
        formatterHTML = new HtmlLogFormatter();
        fileHTML.setFormatter(formatterHTML);
        logger.addHandler(fileHTML);
    }
}