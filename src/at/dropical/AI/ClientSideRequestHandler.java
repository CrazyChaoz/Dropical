package at.dropical.AI;

import at.dropical.Client;
import at.dropical.shared.net.handler.RequestHandler;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.ListRequest;
import at.dropical.shared.net.requests.Request;

public class ClientSideRequestHandler implements RequestHandler {
    private Request r;
    private Client client;

    public ClientSideRequestHandler(Request r, Client client){
        this.r=r;
        this.client=client;

        new Thread(this).start();
    }


    @Override
    public void run() {
        if(r instanceof GameDataContainer)
            client.setCurrentGameDataContainer((GameDataContainer) r);
        else if(r instanceof ListRequest)
            client.setLastListRequest((ListRequest) r);
    }
}
