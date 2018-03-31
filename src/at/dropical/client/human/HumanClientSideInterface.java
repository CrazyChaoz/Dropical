package at.dropical.client.human;

import at.dropical.client.CommonClientSideInterface;
import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

public class HumanClientSideInterface implements CommonClientSideInterface {
    private Transmitter transmitter;

    public HumanClientSideInterface(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

    @Override
    public void toServer(Request r) {
        transmitter.writeRequest(r);
    }

    @Override
    public Request fromServer() {
        return transmitter.readRequest();
    }
}
