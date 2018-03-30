package at.dropical.AI;

import at.dropical.Client;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.ListRequest;
import at.dropical.shared.net.requests.Request;

public class InputRequestHandler extends Thread{
    private Request r;
    private Client client;

    public InputRequestHandler(Request r, Client client){
        this.r=r;
        this.client=client;

        start();
    }


    @Override
    public void run() {
        if(r instanceof GameDataContainer)
            client.setCurrentGameDataContainer((GameDataContainer) r);
        else if(r instanceof ListRequest)
            client.setLastListRequest((ListRequest) r);
    }
}
