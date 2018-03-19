package at.dropical.server.game;

import at.dropical.server.Player;
import at.dropical.server.Viewer;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.transmitter.Transmitter;

import java.util.ArrayList;
import java.util.List;

public class Game{
    //Zuseher
    private List<Viewer> viewers=new ArrayList();

    //Games
    private A_Single_Game[] games;

    //Level
    private int level=0;

    //Time
    private Object time;    //TODO: Implement time

    GameDataContainer gameData=new GameDataContainer();

    /**
     <Constructors>
     **/

    //Classic
    public Game() {
        int playercount=2;
        games=new A_Single_Game[playercount];
    }

    //Variable Players
    public Game(int playercount) {
        games=new A_Single_Game[playercount];
    }

    /**
     <!Constructors>
     **/

    public void addPlayer(String playerName, Transmitter transmitter){
        new A_Single_Game(new Player(transmitter,playerName));
    }

    public void addViewer(Transmitter transmitter){
        viewers.add(new Viewer(transmitter));
    }

    public void updateClients(){
        int i=0;
        for (A_Single_Game game : games) {
            gameData.getPlayernames()[i]=game.getPlayer().getName();
//            gameData.getArenas()[i]=game
            i++;
        }
        for (Viewer viewer : viewers) {
            viewer.getTransmitter().writeRequest(gameData);
        }
    }
}
