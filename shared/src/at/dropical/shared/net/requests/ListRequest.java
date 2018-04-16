package at.dropical.shared.net.requests;

public class ListRequest implements Request{
    private boolean isGameListRequest;

    public ListRequest(boolean isGameListRequest) {
        this.isGameListRequest = isGameListRequest;
    }


    public boolean isGameListRequest() {
        return isGameListRequest;
    }
}
