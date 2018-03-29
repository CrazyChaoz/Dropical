package at.dropical.server.game;

import at.dropical.server.gamefield.TetrisArena;
import at.dropical.server.gamefield.Tetromino;

//TODO: Besseren namen geben
public class A_Single_Game extends Thread {

    public static int STARTVAL_X = -1;
    public static int STARTVAL_Y = -1;

    private String playername;
    private TetrisArena arena;
    private Tetromino currTrock = Tetromino.createRandom();
    private Tetromino nextTrock = Tetromino.createRandom();
    private int currTrockX = STARTVAL_X;
    private int currTrockY = STARTVAL_Y;


    private int punkte = 0;


    public A_Single_Game(String playername) {
        this.playername=playername;
    }

    public String getPlayername() {
        return playername;
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

    public int[][] getVisualArena() {
        return arena.toArray();
    }

    public int getPunkte() {
        return punkte;
    }

    //FUNCTIONALITY

    public boolean moveTrockDown() throws GameOverException {
        boolean retval = arena.placeTetromino(currTrock, currTrockY - 1, currTrockX);

        if (retval == false)
            newTrock();
        else
            currTrockY -= 1;

        int delLines = arena.clearLines();
        punkte += delLines * delLines;


        return retval;
    }

    public void moveTrockLeft() throws GameOverException {
        if (arena.placeTetromino(currTrock, currTrockY, currTrockX - 1))
            currTrockX -= 1;
    }

    public void moveTrockRight() throws GameOverException {
        if (arena.placeTetromino(currTrock, currTrockY, currTrockX + 1) == true)
            currTrockX += 1;

    }

    public void rotateLeft() throws GameOverException {
        if (!arena.placeTetromino(currTrock.rotate(), currTrockY, currTrockX))
            currTrock.rotateBack();
    }

    public void rotateRight() throws GameOverException {
        if (!arena.placeTetromino(currTrock.rotateBack(), currTrockY, currTrockX))
            currTrock.rotate();
    }

    public void dropTrock() throws GameOverException {
        while (moveTrockDown()) ;
    }

    public void placeGhostTrock() throws GameOverException {
        arena.placeGhost(currTrock, currTrockY, currTrockX);

    }

    public void newTrock() {
        currTrock = nextTrock;
        nextTrock = Tetromino.createRandom();
        currTrockX = STARTVAL_X;
        currTrockY = STARTVAL_Y;
    }
}
