package at.dropical.AI;

import at.dropical.AI.SimpleAI;
import at.dropical.server.RequestHandler;
import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class AiTransmitter extends Transmitter {
    private Request cachedRequest=null;

    public AiTransmitter() {
        super(null,null);
    }


    public void toServer(Request r){
        new RequestHandler(r,this);
    }


    @Override
    public void writeRequest(Request r) {
        cachedRequest=r;
    }

    @Override
    @NotNull
    public Request readRequest() {
        while (cachedRequest==null);
        Request r=cachedRequest;
        cachedRequest=null;
        return r;
    }

    @Nullable
    public Request readRequestNonBlocking(){
        Request r=cachedRequest;
        cachedRequest=null;
        return r;
    }
}
