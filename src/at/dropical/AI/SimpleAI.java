package at.dropical.AI;


import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

public class SimpleAI extends Thread {

    private static SimpleAI instance=new SimpleAI();
    public static SimpleAI getInstance() {
        return instance;
    }

    @Override
    public void run() {
        new AiTransmitter().readRequest();
    }
}
