package at.dropical.server.game;

import at.dropical.server.gamefield.TetrisArena;
import at.dropical.server.gamefield.Tetromino;
import at.dropical.server.Player;

//TODO: Besseren namen geben
public class A_Single_Game extends Thread{

    private Player player;
    private TetrisArena arena;
    private Tetromino currTrock;
    private int currTrockX;
    private int currTrockY;

    public A_Single_Game(Player player) {
        this.player=player;
        this.arena = new TetrisArena();
    }

    @Override
    public void run() {

    }
}
