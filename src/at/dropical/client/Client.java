package at.dropical.client;

import at.dropical.shared.net.handler.RequestHandler;
import at.dropical.shared.net.requests.Request;

public abstract class Client implements Runnable {
    private CommonClientSideInterface ccsi;

    public Client(CommonClientSideInterface ccsi) {
        this.ccsi = ccsi;
        new ClientSideRequestHandler(this,ccsi);
        new Thread(this).start();
    }

    protected void toServer(Request r){
        ccsi.toServer(r);
    }

    protected abstract void handleRequest(Request request);

}
