package at.dropical.shared;

import at.dropical.shared.net.requests.Request;

/**
 * The Client "posts" its requests into here
 * The Server constantly polls from here
 */

public class AiRequestCache {
    private Request toServerCachedRequest;
    private Request toClientCachedRequest;

    public Request getToServer() {
        while (toServerCachedRequest==null);
        Request r=toServerCachedRequest;
        toServerCachedRequest=null;
        return r;
    }

    public void toServer(Request toServerCachedRequest) {
        this.toServerCachedRequest = toServerCachedRequest;
    }

    public Request getToClient() {
        while (toClientCachedRequest==null);
        Request r=toClientCachedRequest;
        toClientCachedRequest=null;
        return r;
    }

    public void toClient(Request fromServerCachedRequest) {
        this.toClientCachedRequest = fromServerCachedRequest;
    }
}
