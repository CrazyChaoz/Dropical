package at.dropical.shared.net.transmitter;

import at.dropical.AI.SimpleAI;
import at.dropical.shared.net.requests.Request;

import java.io.InputStream;
import java.io.OutputStream;

public class AiTransmitter extends Transmitter {
    SimpleAI ai;

    public AiTransmitter(SimpleAI ai) {
        super(null,null);
        this.ai=ai;
    }

    @Override
    public void writeRequest(Request r) {
        ai.inbox(r);
    }

    @Override
    public Request readRequest() {
        return ai.outbox();
    }
}
