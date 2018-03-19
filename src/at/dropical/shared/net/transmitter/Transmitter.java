package at.dropical.shared.net.transmitter;

import at.dropical.shared.net.requests.Request;

public interface Transmitter {
    void writeRequest(Request r);
    Request readRequest();
}
