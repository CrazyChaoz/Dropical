import java.util.ArrayList;
import java.util.List;

public class Game{
    //Zuseher
    private List<Viewer> viewers=new ArrayList();

    //Spieler
    private Player[] players;

    //Arenas
    private int[][][] arenas;

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

        players=new Player[playercount];
        arenas=new int[playercount][20][10];
    }

    //Variable Players
    public Game(int playercount) {
        players=new Player[playercount];
        arenas=new int[playercount][20][10];
    }

    //Variable Size
    public Game(int y,int x) {
        int playercount=2;
        players=new Player[playercount];
        arenas=new int[playercount][y][x];
    }

    //Variable Size & Playercount
    public Game(int playercount,int y,int x) {
        players=new Player[playercount];
        arenas=new int[playercount][y][x];
    }

    /**
     <!Constructors>
     **/



}
