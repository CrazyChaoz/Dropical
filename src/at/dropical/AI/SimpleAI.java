package at.dropical.AI;


import at.dropical.Client;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.ListRequest;


public class SimpleAI extends Thread implements Client {

    private static SimpleAI instance=new SimpleAI();
    public static SimpleAI getInstance() {
        return instance;
    }

    private AiTransmitter transmitter=new AiTransmitter();
    private GameDataContainer currentGameDataContainer;
    private ListRequest lastListRequest;


    public void setCurrentGameDataContainer(GameDataContainer currentGameDataContainer) {
        this.currentGameDataContainer = currentGameDataContainer;
    }

    public void setLastListRequest(ListRequest lastListRequest) {
        this.lastListRequest = lastListRequest;
    }

    @Override
    public void run() {
        transmitter.toServer(new ListRequest(true));//transmitter.write();
        transmitter.readRequest();
    }
}
