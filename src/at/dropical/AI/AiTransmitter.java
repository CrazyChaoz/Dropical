package at.dropical.AI;

import at.dropical.server.ServerSideRequestHandler;
import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;


public class AiTransmitter extends Transmitter {
    private SimpleAI instance;

    public AiTransmitter(SimpleAI instance) {
        super(null,null);
        this.instance=instance;
    }

    public void toServer(Request r){
        new ServerSideRequestHandler(r,this);
    }


    //called by server
    @Override
    public void writeRequest(Request r) {
        new ClientSideRequestHandler(r,instance);
    }

    @Override
    public Request readRequest() {
        return null;
    }
}
