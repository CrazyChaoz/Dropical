package at.dropical.server.net;

import at.dropical.server.net.requests.JoinRequest;
import at.dropical.server.net.requests.ListRequest;
import at.dropical.server.net.requests.Request;

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

        }
    }
}
