package at.dropical.client.transmitter;

import at.dropical.shared.LocalRequestCache;
import at.dropical.shared.net.abstracts.SendableItem;
import at.dropical.shared.net.abstracts.Transmitter;

public class LocalTransmitter implements Transmitter {

    private LocalRequestCache cache;

    public LocalTransmitter(LocalRequestCache cache) {
        this.cache=cache;
    }

    @Override
    public void writeRequest(SendableItem r) {
        cache.writeToServer(r);
    }

    @Override
    public SendableItem readRequest() {
        return cache.getToClient();
    }

}
