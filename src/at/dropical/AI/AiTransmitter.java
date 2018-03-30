package at.dropical.AI;

import at.dropical.AI.SimpleAI;
import at.dropical.server.RequestHandler;
import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

public class AiTransmitter extends Transmitter {
    private Request lastRequest=null;

    public AiTransmitter() {
        super(null,null);
    }


//    public void toServer(Request r){
//        new RequestHandler(r,this);
//    }


    @Override
    public void writeRequest(Request r) {
//        SimpleAI.getInstance().inbox(r);
        lastRequest=r;
    }

    @Override
    public Request readRequest() {
        while (lastRequest==null);
        Request r=lastRequest;
        lastRequest=null;
        return r;
    }

    public Request readRequestNonBlocking(){
        Request r=lastRequest;
        lastRequest=null;
        return r;
    }
}
