package at.dropical.server.transmitter;

import at.dropical.server.Server;
import at.dropical.shared.net.abstracts.SendableItem;
import at.dropical.shared.net.abstracts.Transmitter;
import at.dropical.shared.net.requests.HandleInputRequest;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

/**
 * This is a decorator too.
 * It has a non-blocking readRequest().
 * <p>
 * In the background it makes a Thread that constantly tries
 * to receive Requests and writes them into a buffer.
 *
 * TODO RequestHandler: a map of consumers that are called
 * when a request comes.
 */
public class ServerToClientAdapter implements Transmitter {

    private Transmitter underlyingTransmitter;
    private boolean transmitterDied = false;
    private ConcurrentLinkedQueue<HandleInputRequest> inputQueue = new ConcurrentLinkedQueue<>();
    /** For all other requests. */
    private ConcurrentLinkedQueue<SendableItem> requestQueue = new ConcurrentLinkedQueue<>();
    //private Map<Class<SendableItem>, Consumer<SendableItem>> requestHanders;

    /** @param underlyingTransmitter Probably you want
     * to use a ObjectTransmitter. */
    public ServerToClientAdapter(Transmitter underlyingTransmitter) {
        this.underlyingTransmitter = underlyingTransmitter;

        // Receiver loop in own Thread.
        Server.execute(this::recieveRequests);
    }

    /** Loop until you die hard. */
    private void recieveRequests() {
        try {
            for(; ; ) {
                SendableItem req = underlyingTransmitter.readRequest();
                if(req instanceof HandleInputRequest)
                    inputQueue.add((HandleInputRequest) req);
                else
                    requestQueue.add(req);
            }
        } catch(IOException e) {
            transmitterDied = true;
        }
    }

    /** Returns null when no request is in the queue. */
    @Override
    public SendableItem readRequest() throws IOException {
        return requestQueue.poll();
    }

    @Override
    public void writeRequest(SendableItem r) {
        underlyingTransmitter.writeRequest(r);
    }

    public boolean stillConnected() {
        return !transmitterDied;
    }

    /** So kann gleich ein foreach gemacht werden. */
    public ConcurrentLinkedQueue<HandleInputRequest> getInputQueue() {
        return inputQueue;
    }
}
