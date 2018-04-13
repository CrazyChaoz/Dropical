package at.dropical.shared.net.requests;

import java.util.ArrayList;
import java.util.List;

public class ListDataContainer implements Request{

    private List<String> listNames=null;

    public void addName(String name){
        if(listNames==null)
            listNames=new ArrayList<>();
        listNames.add(name);
    }
    public List<String> getGameNames() {
        return listNames;
    }

}