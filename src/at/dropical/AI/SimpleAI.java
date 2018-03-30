package at.dropical.AI;


import at.dropical.shared.net.requests.InputDataContainer;
import at.dropical.shared.net.requests.ListRequest;
import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

public class SimpleAI extends Thread {

    private static SimpleAI instance=new SimpleAI();
    public static SimpleAI getInstance() {
        return instance;
    }

    private AiTransmitter transmitter=new AiTransmitter();


    @Override
    public void run() {
        transmitter.toServer(new ListRequest(true));//transmitter.write();
        transmitter.readRequest();
    }
}
