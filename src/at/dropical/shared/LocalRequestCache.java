package at.dropical.shared;

import at.dropical.shared.net.requests.Request;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The Client "posts" its requests into here
 * The Server constantly polls from here
 * and vice versa
 */

public class LocalRequestCache {
    private Queue<Request> toServerCachedRequest=new ConcurrentLinkedQueue<>();
    private Queue<Request> toClientCachedRequest=new ConcurrentLinkedQueue<>();

    public Request getToServer() {
        while (toServerCachedRequest.isEmpty());
        return toServerCachedRequest.poll();
    }

    public void writeToServer(Request toServerCachedRequest) {
        this.toServerCachedRequest.offer(toServerCachedRequest);
    }

    public Request getToClient() {
        while (toClientCachedRequest.isEmpty());
        return toClientCachedRequest.poll();
    }

    public void writeToClient(Request fromServerCachedRequest) {
        this.toClientCachedRequest.offer(fromServerCachedRequest);
    }
}
