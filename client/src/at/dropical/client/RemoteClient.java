package at.dropical.client;

import at.dropical.shared.net.transmitter.ServerSideTransmitter;

import java.io.IOException;
import java.net.Socket;

public abstract class RemoteClient {
    public RemoteClient(Socket server) throws IOException {
        super(new GeneralClientSideTransmitter(server));
    }

    //when more than one client side transmitters are implemented
    public RemoteClient(ServerSideTransmitter specialTransmitter) {
        super(specialTransmitter);
    }
}
