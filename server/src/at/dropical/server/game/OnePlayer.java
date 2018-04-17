package at.dropical.server.game;

import at.dropical.server.gamefield.TetrisArena;
import at.dropical.server.gamefield.Tetromino;

/**
 * This class manages one TetrisArena, one
 * current tetromino and one nextTetromino.
 * And information about the player.
 *
 * Some methods throw a GameOverException when
 * a game over occurs.
 */
public class OnePlayer extends Thread {

    private static int STARTVAL_X = TetrisArena.width/2 -Tetromino.size/2;
    private static int STARTVAL_Y = -1;

    private String playername;
    private int points = 0;

    private TetrisArena arena = new TetrisArena(playername);
    private Tetromino tetromino = Tetromino.createRandom();
    private Tetromino nextTetromino = Tetromino.createRandom();
    private int currTetrX = STARTVAL_X;
    private int currTetrY = STARTVAL_Y;

    public OnePlayer(String playername) {
        this.playername = playername;
    }

    //FUNCTIONALITY

    /** Lowering the Tetromino a block.
     * If that is not possible, placeTetromino(). */
    private void moveDownwardsOrPlace() throws GameOverException {
        if (arena.checkTetromino(tetromino, currTetrY + 1, currTetrX, true)) {
            currTetrY++;
        } else
            placeTetromino();
    }

    /** Places the current one and makes a new Tetromino.
     * @throws GameOverException If the placing fails. */
    private void placeTetromino() throws GameOverException {
        arena.placeTetromino(tetromino, currTetrY, currTetrX);
        newNextTetromino();
    }

    /** The nextTetromino gets placed at the top of the arena.
     * If the place is obscured, gameOver is set. */
    private void newNextTetromino() throws GameOverException {
        tetromino = nextTetromino;
        // TODO give the different players the same RNG
        nextTetromino = Tetromino.createRandom();
        currTetrY = STARTVAL_Y;
        currTetrX = STARTVAL_X;

        if(! arena.checkTetromino(tetromino, currTetrY, currTetrX, true))
            throw new GameOverException(playername);
    }


    /** If it is allowed, decrement currTetrY */
    public void moveDown() throws GameOverException {
        if (arena.checkTetromino(tetromino, currTetrY + 1, currTetrX, true))
            currTetrY++;
    }
    /** go down as long as possible and place the Tetromino */
    public void dropTetromino() throws GameOverException {
        while (arena.checkTetromino(tetromino, currTetrY + 1, currTetrX, true))
            currTetrY++;
        placeTetromino();
    }

    public void moveLeft() {
        if (arena.checkTetromino(tetromino, currTetrY, currTetrX - 1, true))
            currTetrX -= 1;
    }
    public void moveRight() {
        if (arena.checkTetromino(tetromino, currTetrY, currTetrX + 1, true))
            currTetrX += 1;

    }

    public void rotateLeft() {
        if (!arena.checkTetromino(tetromino.rotate(), currTetrY, currTetrX, true))
            tetromino.rotateBack();
    }
    public void rotateRight() {
        if (!arena.checkTetromino(tetromino.rotateBack(), currTetrY, currTetrX, true))
            tetromino.rotate();
    }


    /** Getters & Setters **/

    public String getPlayername() {
        return playername;
    }

    public Tetromino getTetromino() {
        return tetromino;
    }
    public int[][] getNextTetromino() {
        return nextTetromino.toArray();
    }
    public int getCurrTetrX() {
        return currTetrX;
    }
    public int getCurrTetrY() {
        return currTetrY;
    }
    public int[][] getVisualArena() {
        return arena.toArray();
    }
    public int getPoints() {
        return points;
    }
}
