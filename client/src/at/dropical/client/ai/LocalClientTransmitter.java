package at.dropical.client.ai;

import at.dropical.shared.LocalRequestCache;
import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

public class LocalClientTransmitter extends Transmitter {
    private LocalRequestCache cache;

    public LocalClientTransmitter(LocalRequestCache cache) {
        super(null,null);
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
