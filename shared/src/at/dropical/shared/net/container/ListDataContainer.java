package at.dropical.shared.net.container;

import at.dropical.shared.GameState;
import at.dropical.shared.net.abstracts.Container;

import java.util.ArrayList;
import java.util.List;

public class ListDataContainer extends Container {

    private List<String> listNames=new ArrayList<>();

    public ListDataContainer(GameState currentState) {
        super(currentState);
    }

    public void addName(String name){
        listNames.add(name);
    }
    public List<String> getNames() {
        return listNames;
    }

}
