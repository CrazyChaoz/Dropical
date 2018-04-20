package at.dropical.server;

import at.dropical.shared.LocalRequestCache;
import at.dropical.shared.net.requests.Request;


public class LocalServerTransmitter extends ServerSideTransmitter {
    private LocalRequestCache requestCache;

    public LocalServerTransmitter(LocalRequestCache requestCache) {
        super(null, null);
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
