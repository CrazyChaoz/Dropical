import at.dropical.server.gamefield.TetrisArena;
import at.dropical.server.gamefield.Tetromino;


//TODO: Besseren namen geben
public class A_Single_Game {

    private Player player;
    private TetrisArena arena;
    private Tetromino currTrock;
    private int[][] nextTrock;
    private int currTrockX;
    private int currTrockY;

    public A_Single_Game() {
        this.arena = new TetrisArena();
        this.currTrock = currTrock;
        this.nextTrock = nextTrock;
    }
}
