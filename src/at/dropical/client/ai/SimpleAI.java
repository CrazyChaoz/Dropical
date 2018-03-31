package at.dropical.client.ai;


import at.dropical.client.ai.ArtificialIntelligence;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.ListRequest;


public class SimpleAI extends ArtificialIntelligence {

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
//        (new ListRequest(true));
    }
}
