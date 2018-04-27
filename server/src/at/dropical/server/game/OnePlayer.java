package at.dropical.server.game;

import at.dropical.server.gamefield.TetrisArena;
import at.dropical.server.gamefield.Tetromino;

/**
 * This class manages one TetrisArena, one
 * current tetromino and one nextTetromino.
 * And information about the player.
 * <p>
 * Some methods throw a GameOverException when
 * a game over occurs.
 */
public class OnePlayer {

    private static int STARTVAL_X = TetrisArena.width / 2 - Tetromino.size / 2;
    private static int STARTVAL_Y = -1;


    //    Level
    private int level = 0;

    //    Score
    private int score = 0;

    //    Playername
    private String playername;

    //
    private int ticks=0;


    private TetrisArena arena;
    private Tetromino tetromino = Tetromino.createRandom();
    private Tetromino nextTetromino = Tetromino.createRandom();
    private int currTetrX = STARTVAL_X;
    private int currTetrY = STARTVAL_Y;

    public OnePlayer(String playername) {
        this.playername = playername;
        this.arena = new TetrisArena(playername);
    }

    //FUNCTIONALITY

    /** Goes automatically down after time.
     * fixme At level 5 the algorithm for going faster breaks. */
    public boolean update() throws GameOverException, LinesClearedException {
        ticks++;
        if(ticks%((100-level*level*level))==0){
            ticks=0;
            moveDown();
            return true;
        }
        return false;
    }

    /**
     * Places the current one and makes a new Tetromino.
     * !! Make sure nothing important is after this function,
     * !! because it will not be executed if an exception is thrown.
     *
     * @throws GameOverException If the placing fails.
     */
    private void placeTetromino() throws GameOverException, LinesClearedException {
        arena.placeTetromino(tetromino, currTetrY, currTetrX);
        newNextTetromino();
        arena.clearLines();
    }

    /**
     * The nextTetromino gets placed at the top of the arena.
     * If the place is obscured, gameOver is set.
     */
    private void newNextTetromino() throws GameOverException {
        tetromino = nextTetromino;
        // TODO give the different players the same RNG
        nextTetromino = Tetromino.createRandom();
        currTetrY = STARTVAL_Y;
        currTetrX = STARTVAL_X;

        if (!arena.checkTetromino(tetromino, currTetrY, currTetrX, true))
            throw new GameOverException(playername);
    }


    /**
     * Lowering the Tetromino a block.
     * If that is not possible, placeTetromino().
     */
    public void moveDown() throws GameOverException, LinesClearedException {
        if (arena.checkTetromino(tetromino, currTetrY + 1, currTetrX, true)) {
            currTetrY++;
        } else
            placeTetromino();
    }

    /**
     * go down as long as possible and place the Tetromino
     */
    public void dropTetromino() throws GameOverException, LinesClearedException {
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


    /**
     * Getters & Setters
     **/

    public String getPlayername() {
        return playername;
    }

    public Tetromino getCurrTetromino() {
        return tetromino;
    }

    public Tetromino getNextTetromino() {
        return nextTetromino;
    }

    public int getCurrTetrX() {
        return currTetrX;
    }

    public int getCurrTetrY() {
        return currTetrY;
    }

    public int[][] getArena() {
        return arena.toArray();
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

}
