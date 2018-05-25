package at.dropical.server.transmitter;

import at.dropical.shared.LocalRequestCache;
import at.dropical.shared.net.abstracts.SendableItem;


public class LocalServerTransmitter extends ServerSideTransmitter {
    private LocalRequestCache requestCache;

    public LocalServerTransmitter(LocalRequestCache requestCache) {
        this.requestCache = requestCache;
    }

    @Override
    public void writeRequest(SendableItem r) {
        requestCache.writeToClient(r);
    }

    @Override
    public SendableItem readRequest() {
        return requestCache.getToServer();
    }
}
