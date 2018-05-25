package at.dropical.shared.net.abstracts;

import java.io.IOException;

public interface Transmitter {
    SendableItem readRequest() throws IOException, ClassNotFoundException;
    void writeRequest(SendableItem r);
}
