package at.dropical.shared.net.transmitter;

import at.dropical.shared.net.requests.Request;

import java.io.IOException;

public interface Transmitter {
    void writeRequest(Request r) throws IOException;
    Request readRequest() throws IOException, ClassNotFoundException;
}
