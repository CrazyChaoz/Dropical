package at.dropical.client;

import at.dropical.shared.net.handler.RequestHandler;

public class ClientSideRequestHandler implements RequestHandler {

    private Client client;
    private CommonClientSideInterface ccsi;


    public ClientSideRequestHandler(Client client, CommonClientSideInterface ccsi) {
        this.client = client;
        this.ccsi = ccsi;
        new Thread(this).start();
    }


    @Override
    public void run() {
        for (; ; ) {
            client.handleRequest(ccsi.fromServer());
        }
    }
}
