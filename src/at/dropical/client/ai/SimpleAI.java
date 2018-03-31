package at.dropical.client.ai;


import at.dropical.Client;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.ListRequest;


public class SimpleAI implements Client {

    private static SimpleAI instance=new SimpleAI();
    public static SimpleAI getInstance() {
        return instance;
    }

    private AiRequestCache requestCache;
    private GameDataContainer currentGameDataContainer;
    private ListRequest lastListRequest;


    public void setCurrentGameDataContainer(GameDataContainer currentGameDataContainer) {
        this.currentGameDataContainer = currentGameDataContainer;
    }

    public void setLastListRequest(ListRequest lastListRequest) {
        this.lastListRequest = lastListRequest;
    }

    public AiRequestCache getRequestCache() {
        return requestCache;
    }

    @Override
    public void run() {

    }
}
