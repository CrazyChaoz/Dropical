package at.dropical.client;

import at.dropical.client.CommonClientSideInterface;
import at.dropical.shared.net.requests.Request;

public abstract class Client implements Runnable {
    private CommonClientSideInterface cci;

    public Client(CommonClientSideInterface cci) {
        this.cci = cci;
    }

    protected void toServer(Request r){
        cci.toServer(r);
    }

    private Request fromServer(){
        return cci.fromServer();
    }

}
