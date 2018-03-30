package at.dropical;

import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.ListRequest;
import at.dropical.shared.net.requests.Request;

public interface Client {
    void setCurrentGameDataContainer(GameDataContainer request);
    void setLastListRequest(ListRequest request);
}
