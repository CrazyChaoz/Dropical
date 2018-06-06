package at.dropical.server;

import at.dropical.server.transmitter.ServerSideTransmitter;
import at.dropical.shared.net.abstracts.Request;

import java.io.IOException;
import java.util.logging.Level;

public class Loop {
    public Loop(ServerSideTransmitter transmitter) throws IOException, ClassNotFoundException, ClassCastException {
        for(; ; ) {
            Server.LOGGER.log(Level.INFO, "SendableItem Received");
            Server.serverExecutor.execute(new ServerSideRequestHandler((Request) transmitter.readRequest(), transmitter));
//            new ServerSideRequestHandler((Request) transmitter.readRequest(), transmitter).run();   //performance test
        }
    }
}
