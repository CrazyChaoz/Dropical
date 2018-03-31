package at.dropical.client.ai;

import at.dropical.client.Client;
import at.dropical.shared.AiRequestCache;

public abstract class ArtificialIntelligence extends Client {
    private final AiRequestCache requestCache=new AiRequestCache();

    public ArtificialIntelligence() {
        super(new AiClientSideInterface());
    }

    public AiRequestCache getRequestCache() {
        return requestCache;
    }
}
