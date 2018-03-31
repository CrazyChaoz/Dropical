package at.dropical.shared.net.transmitter;

import at.dropical.Client;
import at.dropical.client.ClientSideRequestHandler;
import at.dropical.client.ai.SimpleAI;
import at.dropical.shared.net.requests.Request;


public class ServerSideAiTransmitter extends Transmitter {
    private SimpleAI ai;

    public ServerSideAiTransmitter(SimpleAI ai) {
        super(null, null);
        this.ai = ai;
    }

    @Override
    public void writeRequest(Request r) {
        new ClientSideRequestHandler(r, ai);
    }

    @Override
    public Request readRequest() {
        while (ai.getRequestCache().getCachedRequest()==null);
        Request r=ai.getRequestCache().getCachedRequest();
        ai.getRequestCache().setCachedRequest(null);
        return r;
    }
}
