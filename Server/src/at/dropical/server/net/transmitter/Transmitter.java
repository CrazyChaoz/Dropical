package at.dropical.server.net.transmitter;

import at.dropical.server.net.requests.Request;

public interface Transmitter {
    void writeRequest(Request r);
    Request readRequest();
}
