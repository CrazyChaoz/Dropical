package at.dropical.client.human;

import at.dropical.client.Client;
import at.dropical.client.human.transmitter.GeneralClientSideTransmitter;
import at.dropical.shared.net.transmitter.Transmitter;

import java.io.IOException;
import java.net.Socket;

public abstract class HumanClient extends Client {
    public HumanClient(Socket server) throws IOException {
        super(new GeneralClientSideTransmitter(server));
    }

    //when more than one client side transmitters are implemented
    public HumanClient(Transmitter specialTransmitter) {
        super(specialTransmitter);
    }
}
