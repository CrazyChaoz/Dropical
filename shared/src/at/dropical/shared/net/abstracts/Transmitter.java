package at.dropical.shared.net.abstracts;

import java.io.IOException;

public interface Transmitter {
    /* May block to wait for a request
     * or may return null and not block. */
    SendableItem readRequest() throws IOException;
    void writeRequest(SendableItem r);
}
