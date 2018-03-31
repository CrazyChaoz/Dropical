package at.dropical.client.ai;

import at.dropical.shared.net.requests.Request;


public class AiRequestCache {
    private SimpleAI instance;
    private Request cachedRequest;

    public AiRequestCache(SimpleAI instance) {
        this.instance = instance;
    }

    public void setCachedRequest(Request cachedRequest) {
        this.cachedRequest = cachedRequest;
    }

    public Request getCachedRequest() {
        return cachedRequest;
    }
}
