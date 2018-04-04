package at.dropical.client.ai;

import at.dropical.client.Client;
import at.dropical.shared.AiRequestCache;

public abstract class ArtificialIntelligence extends Client {
    private AiRequestCache requestCache;
    public ArtificialIntelligence(AiRequestCache requestCache) {
        super(new AiSideTransmitter(requestCache));
        this.requestCache=requestCache;
    }

    public AiRequestCache getRequestCache() {
        return requestCache;
    }
}
