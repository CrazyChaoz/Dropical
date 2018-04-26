package at.dropical.client.transmitter;

import at.dropical.shared.LocalRequestCache;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.Transmitter;

public class LocalTransmitter implements Transmitter {

    private LocalRequestCache cache;

    public LocalTransmitter(LocalRequestCache cache) {
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
