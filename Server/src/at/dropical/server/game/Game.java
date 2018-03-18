package at.dropical.server.game;

import at.dropical.server.net.Viewer;

import java.util.ArrayList;
import java.util.List;

public class Game{
    //Zuseher
    private List<Viewer> viewers=new ArrayList();

    //Games
    private A_Single_Game[] game;

    //Level
    private int level=0;

    //Time
    private Object time;    //TODO: Implement time


    /**
     <Constructors>
     **/

    //Classic
    public Game() {
        int playercount=2;
        game=new A_Single_Game[playercount];
    }

    //Variable Players
    public Game(int playercount) {
        game=new A_Single_Game[playercount];
    }

    /**
     <!Constructors>
     **/

}
