package at.dropical.server;

import at.dropical.shared.net.requests.JoinRequest;
import at.dropical.shared.net.requests.ListRequest;
import at.dropical.shared.net.requests.Request;

public class RequestHandler extends Thread {
    private Request request;
    public RequestHandler(Request request) {
        this.request=request;
        this.start();
    }

    @Override
    public void run() {
        if(request instanceof ListRequest){

        }else if(request instanceof JoinRequest){
            ((JoinRequest) request).getGameID();
        }
    }
}
