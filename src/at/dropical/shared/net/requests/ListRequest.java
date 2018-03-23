package at.dropical.shared.net.requests;

import java.util.ArrayList;
import java.util.List;

public class ListRequest implements Request{
    private boolean isGameListRequest;
    private List<String> listNames=null;

    public ListRequest(boolean isGameListRequest) {
        this.isGameListRequest = isGameListRequest;
    }

    public List<String> getGameNames() {
        return listNames;
    }

    public void addName(String name){
        if(listNames==null)
            listNames=new ArrayList<>();
        listNames.add(name);
    }

    public boolean isGameListRequest() {
        return isGameListRequest;
    }
}
