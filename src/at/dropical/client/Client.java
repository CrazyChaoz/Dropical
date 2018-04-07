package at.dropical.client;

import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

public abstract class Client implements Runnable {
    private Transmitter transmitter;

    public Client(Transmitter transmitter) {
        this.transmitter=transmitter;
        new ClientSideRequestHandler(this,transmitter);
        new Thread(this).start();
    }

    protected void toServer(Request r){
        transmitter.writeRequest(r);
    }

    protected abstract void handleRequest(Request request);

}
