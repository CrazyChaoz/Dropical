package at.dropical.shared.net.container;

import at.dropical.shared.net.abstracts.Container;

import java.util.ArrayList;
import java.util.List;

public class ListDataContainer extends Container {

    private List<String> listNames=null;

    public ListDataContainer() {
        super(null);
    }

    public void addName(String name){
        if(listNames==null)
            listNames=new ArrayList<>();
        listNames.add(name);
    }
    public List<String> getGameNames() {
        return listNames;
    }

}