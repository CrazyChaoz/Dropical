package at.dropical.client.ai;

import at.dropical.client.Client;
import at.dropical.shared.LocalRequestCache;

public abstract class LocalClient extends Client {

    private LocalRequestCache requestCache;

    public LocalClient(LocalRequestCache requestCache) {
        super(new LocalClientTransmitter(requestCache));

//        Server.instance().addLocalClient(requestCache);
        this.requestCache = requestCache;
    }

    public LocalRequestCache getRequestCache() {
        return requestCache;
    }
}
