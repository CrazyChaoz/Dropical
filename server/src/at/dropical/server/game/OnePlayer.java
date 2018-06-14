package at.dropical.server.game;

import at.dropical.server.gamefield.TetrisArena;
import at.dropical.server.gamefield.Tetromino;

import java.util.function.BiConsumer;

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

    private String playername;

    /* Function to execute when some lines are cleared
     * to add lines to other players.
     * It takes the playername and the number of lines cleared. */
    private BiConsumer<String, Integer> onLinesCleared;

    private int level = 0;
    private int score = 0;
    private int ticks=0;

    private TetrisArena arena;
    private Tetromino tetromino = Tetromino.createRandom();
    private Tetromino nextTetromino = Tetromino.createRandom();
    private int currTetrX = STARTVAL_X;
    private int currTetrY = STARTVAL_Y;

    /** @param onLinesCleared (Lambda) function that addLines to other players. */
    public OnePlayer(String playername, BiConsumer<String, Integer> onLinesCleared) {
        this.playername = playername;
        this.arena = new TetrisArena(playername);
        this.onLinesCleared = onLinesCleared;
    }

    //FUNCTIONALITY


    /**
     * Places the current one and makes a new Tetromino.
     * Automatically clears lines.
     *
     * @throws GameOverException If the placing fails.
     */
    private void placeTetromino() throws GameOverException {
        boolean ok = arena.placeTetromino(tetromino, currTetrY, currTetrX);
        newNextTetromino();

        if(!ok)
            throw new GameOverException(playername);

        int lines = arena.clearLines();
        if(lines >= 1)
            onLinesCleared.accept(playername, lines);
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

    /** Adds random lines and pushes up the tetromino. */
    void addLines(Integer lines) throws GameOverException {
        //First lift Tetromino up but don't go up to high.
        int newYpos = Math.max(currTetrY - lines, STARTVAL_Y);
        if(arena.checkTetromino(tetromino, newYpos, currTetrX, true))
            currTetrY = newYpos;

        if(arena.addLines(lines))
            throw new GameOverException(playername);
    }


    /**
     * Lowering the Tetromino a block.
     * If that is not possible, placeTetromino().
     */
    public void moveDown() throws GameOverException {
        if (arena.checkTetromino(tetromino, currTetrY + 1, currTetrX, true)) {
            currTetrY++;
        } else
            placeTetromino();
    }

    /**
     * go down as long as possible and place the Tetromino
     */
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


    public boolean update() throws GameOverException {
        ticks+=2;
        if(ticks%((100-level*level*level)*2)==(ticks/2))
            return true;
        else if(ticks%((100-level*level*level)*2)==0){
            ticks=0;
            moveDown();
            return true;
        }
        return false;
    }
}
