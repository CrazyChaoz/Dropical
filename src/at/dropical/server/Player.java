package at.dropical.server;

import at.dropical.shared.net.transmitter.Transmitter;

public class Player{
    private Transmitter transmitter;
    private String name;


    public Player(Transmitter transmitter, String name) {
        this.transmitter = transmitter;
        this.name=name;
    }


    public Transmitter getTransmitter() {
        return transmitter;
    }

    public String getName() {
        return name;
    }

}
