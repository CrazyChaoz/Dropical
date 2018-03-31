package at.dropical.client.ai;

import at.dropical.client.CommonClientSideInterface;
import at.dropical.shared.AiRequestCache;
import at.dropical.shared.net.requests.Request;

public class AiClientSideInterface implements CommonClientSideInterface {
    private AiRequestCache cache=new AiRequestCache();

    @Override
    public void toServer(Request r) {
        cache.toServer(r);
    }

    @Override
    public Request fromServer() {
        return cache.getToClient();
    }
}
