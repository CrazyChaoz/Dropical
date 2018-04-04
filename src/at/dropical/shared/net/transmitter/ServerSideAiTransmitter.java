package at.dropical.shared.net.transmitter;

import at.dropical.client.ai.ArtificialIntelligence;
import at.dropical.shared.net.requests.Request;


public class ServerSideAiTransmitter extends Transmitter {
    private ArtificialIntelligence ai;

    public ServerSideAiTransmitter(ArtificialIntelligence ai) {
        super(null, null);
        this.ai = ai;
    }

    @Override
    public void writeRequest(Request r) {
        ai.getRequestCache().writeToClient(r);
    }

    @Override
    public Request readRequest() {
        return ai.getRequestCache().getToServer();
    }
}
