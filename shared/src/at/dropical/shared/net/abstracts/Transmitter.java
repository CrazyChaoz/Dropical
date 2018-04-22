package at.dropical.shared.net.abstracts;

import at.dropical.shared.net.abstracts.Request;

import java.io.IOException;

public interface Transmitter {
    Request readRequest() throws IOException, ClassNotFoundException;
    void writeRequest(Request r);
}
