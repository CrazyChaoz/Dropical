package at.dropical.server.game;

import at.dropical.server.Player;
import at.dropical.server.gamefield.TetrisArena;
import at.dropical.server.gamefield.Tetromino;

//TODO: Besseren namen geben
public class A_Single_Game extends Thread{

    private Player player;
    private TetrisArena arena;
    private Tetromino currTrock=Tetromino.createRandom();
    private Tetromino nextTrock=Tetromino.createRandom();
    private int currTrockX;
    private int currTrockY;


    public A_Single_Game(Player player) {
        this.player=player;
    }

    public Player getPlayer() {
        return player;
    }

    public int[][] getNextTrock() {
        return nextTrock.toArray();
    }

    public int getCurrTrockX() {
        return currTrockX;
    }

    public int getCurrTrockY() {
        return currTrockY;
    }

}
