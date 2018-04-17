package at.dropical.server.transmitter;

import at.dropical.shared.LocalRequestCache;
import at.dropical.shared.net.requests.Request;


public class LocalServerTransmitter extends ServerTransmitter {
    private LocalRequestCache requestCache;

    public LocalServerTransmitter(LocalRequestCache requestCache) {
        this.requestCache = requestCache;
    }

    @Override
    public void writeRequest(Request r) {
        requestCache.writeToClient(r);
    }

    @Override
    public Request readRequest() {
        return requestCache.getToServer();
    }
}
