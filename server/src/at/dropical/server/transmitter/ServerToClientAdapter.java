package at.dropical.server.transmitter;

import at.dropical.server.Server;
import at.dropical.shared.net.abstracts.SendableItem;
import at.dropical.shared.net.abstracts.Transmitter;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This is a decorator too.
 * It has a non-blocking readRequest().
 * <p>
 * In the background it makes a Thread that constantly tries
 * to receive Requests and writes them into a buffer.
 */
public class ServerToClientAdapter implements Transmitter {

    private Transmitter underlyingTransmitter;
    private ConcurrentLinkedQueue<SendableItem> requestQueue = new ConcurrentLinkedQueue<>();
    private boolean transmitterDied = false;

    /** @param underlyingTransmitter Probably you want
     * to use a ObjectTransmitter. */
    public ServerToClientAdapter(Transmitter underlyingTransmitter) {
        this.underlyingTransmitter = underlyingTransmitter;

        // Receiver loop in own Thread.
        Server.instance().execute(() -> {
            try {
                for(; ; )
                    requestQueue.add(underlyingTransmitter.readRequest());
            } catch(IOException e) {
                transmitterDied = true;
            }
        });
    }

    /**
     * Returns null when no request is in the buffer.
     */
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
}
