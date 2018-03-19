package at.dropical.server;

import at.dropical.shared.net.transmitter.Transmitter;

public class Viewer {
    private Transmitter transmitter;

    public Viewer(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

    public Transmitter getTransmitter() {
        return transmitter;
    }
}
