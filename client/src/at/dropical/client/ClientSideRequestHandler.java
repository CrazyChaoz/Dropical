package at.dropical.client;

import at.dropical.shared.net.handler.RequestHandler;
import at.dropical.shared.net.transmitter.Transmitter;

import java.io.IOException;

public class ClientSideRequestHandler implements RequestHandler {

    private Client client;
    private Transmitter transmitter;


    public ClientSideRequestHandler(Client client, Transmitter t) {
        this.client = client;
        this.transmitter = t;
        new Thread(this).start();
    }


    @Override
    public void run() {
        for (; ; ) {
            try {
                client.handleRequest(transmitter.readRequest());
            } catch (IOException|ClassNotFoundException e) {
                break;
            }
        }
    }
}
