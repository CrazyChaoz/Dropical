package at.dropical.server.transmitter;

import at.dropical.shared.LocalRequestCache;
import at.dropical.shared.net.abstracts.SendableItem;
import at.dropical.shared.net.abstracts.Transmitter;


public class LocalServerTransmitter implements Transmitter {
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
