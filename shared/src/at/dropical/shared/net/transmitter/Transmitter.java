package at.dropical.shared.net.transmitter;

import at.dropical.shared.net.requests.Request;

import java.io.IOException;

public interface Transmitter {
    Request readRequest() throws IOException, ClassNotFoundException;
    void writeRequest(Request r);
}
