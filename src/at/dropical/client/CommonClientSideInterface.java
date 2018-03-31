package at.dropical.client;

import at.dropical.shared.net.requests.Request;

public interface CommonClientSideInterface {
    void toServer(Request r);
    Request fromServer();
}
