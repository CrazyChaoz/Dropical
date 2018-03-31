package at.dropical;

import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.ListRequest;

public interface Client extends Runnable {
    void setCurrentGameDataContainer(GameDataContainer request);
    void setLastListRequest(ListRequest request);
}
