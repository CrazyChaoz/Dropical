package at.dropical.shared;

import at.dropical.shared.net.requests.Request;

/**
 * The Client "posts" its requests into here
 * The Server constantly polls from here
 * and vice versa
 */

public class LocalRequestCache {
    private Request toServerCachedRequest;
    private Request toClientCachedRequest;

    public Request getToServer() {
        while (toServerCachedRequest==null);
        Request r=toServerCachedRequest;
        toServerCachedRequest=null;
        return r;
    }

    public void writeToServer(Request toServerCachedRequest) {
        this.toServerCachedRequest = toServerCachedRequest;
    }

    public Request getToClient() {
        while (toClientCachedRequest==null);
        Request r=toClientCachedRequest;
        toClientCachedRequest=null;
        return r;
    }

    public void writeToClient(Request fromServerCachedRequest) {
        this.toClientCachedRequest = fromServerCachedRequest;
    }
}
