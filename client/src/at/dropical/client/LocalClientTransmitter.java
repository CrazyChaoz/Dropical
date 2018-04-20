package at.dropical.client;

import at.dropical.shared.LocalRequestCache;
import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

public class LocalClientTransmitter implements Transmitter {
    private LocalRequestCache cache;

    public LocalClientTransmitter(LocalRequestCache cache) {
        this.cache=cache;
    }

    @Override
    public void writeRequest(Request r) {
        cache.writeToServer(r);
    }

    @Override
    public Request readRequest() {
        return cache.getToClient();
    }

}
