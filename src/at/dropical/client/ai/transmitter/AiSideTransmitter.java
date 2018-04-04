package at.dropical.client.ai.transmitter;

import at.dropical.shared.AiRequestCache;
import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

public class AiSideTransmitter extends Transmitter {
    private AiRequestCache cache;

    public AiSideTransmitter(AiRequestCache cache) {
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

    public AiRequestCache getCache() {
        return cache;
    }
}
