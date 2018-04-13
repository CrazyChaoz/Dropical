package at.dropical.shared.net.requests;

import java.util.ArrayList;
import java.util.List;

public class ListRequest implements Request{
    private boolean isGameListRequest;

    public ListRequest(boolean isGameListRequest) {
        this.isGameListRequest = isGameListRequest;
    }


    public boolean isGameListRequest() {
        return isGameListRequest;
    }
}
