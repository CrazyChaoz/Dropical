package at.dropical.shared;

import at.dropical.shared.net.abstracts.SendableItem;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The Client "posts" its requests into here
 * The Server constantly polls from here
 * and vice versa
 */

public class LocalRequestCache {
    private Queue<SendableItem> toServerCachedRequest=new ConcurrentLinkedQueue<>();
    private Queue<SendableItem> toClientCachedRequest=new ConcurrentLinkedQueue<>();

    public SendableItem getToServer() {
        while (toServerCachedRequest.isEmpty());
        return toServerCachedRequest.poll();
    }

    public void writeToServer(SendableItem toServerCachedRequest) {
        this.toServerCachedRequest.offer(toServerCachedRequest);
    }

    public SendableItem getToClient() {
        while (toClientCachedRequest.isEmpty());
        return toClientCachedRequest.poll();
    }

    public void writeToClient(SendableItem fromServerCachedRequest) {
        this.toClientCachedRequest.offer(fromServerCachedRequest);
    }
}
