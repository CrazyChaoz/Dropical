package at.dropical.wolliAI.bestPossibility;
// Created by julian on 26.04.18.


import at.dropical.wolliAI.gamefieldCopy.TetrisArena;
import at.dropical.wolliAI.gamefieldCopy.Tetromino;
import at.dropical.wolliAI.gamefieldCopy.TetrominoTypes;

/**
 * This is a game representation with a lot
 * more functionality than just the TetrisArena.
 *
 * This includes a TetrisArena, a Tetromino
 * and posH and posW of later.
 */
public class GameField {

    private TetrisArena arena;
    private Tetromino mino;
    private int posH;
    private int posW;

    /** Constructor for the values that are transmitted. */
    GameField(int[][] arena, int[][] tetromino, int xPos, int yPos) {
        this.arena = new TetrisArena(arena, true);
        this.mino = whatTypeIsThis(tetromino);
        posH = yPos;
        posW = xPos;
    }

    /** For cloning. */
    private GameField(TetrisArena arena, Tetromino mino, int posH, int posW) {
        this.arena = arena;
        this.mino = mino;
        this.posH = posH;
        this.posW = posW;
    }

    /** This finds out what tetrominoType and what rotation was
     * transmitted to this AI.
     * The type is sadly not a part of the protocol. */
    private static Tetromino whatTypeIsThis(int[][] transmittedTetromino) {
        // Iterate through all types and rotations.
        for(int[][][] type : TetrominoTypes.everything) {
            for(int rotation = 0; rotation < type.length; rotation++) {
                int[][] field = type[rotation];

                // Check if it is the same.
                boolean same = true;
                for(int i = 0; i < field.length; i++) {
                    for(int j = 0; j < field[i].length; j++) {
                        if(transmittedTetromino[i][j] != field[i][j])
                            same = false;
                    }
                }
                // If same, we have found what we were looking for.
                if(same) {
                    // Jumps out of the loops.
                    return new Tetromino(type, rotation);
                }
            }
        }
        // If an unknown tetromino was transmitted.
        return new Tetromino(TetrominoTypes.emptyMatrix, 0);
    }

    /**@return the number of spaces that between the tetromino and
     * the existing blocks in the arena. */
    int countSpacesBelowTetromino() {
        return arena.countSpacesBelowTetromino(mino, posH, posW);
    }

    /** When cloning, a new tetromino has to be created because
     * of the rotation. The arena is shared. */
    @Override
    public GameField clone() {
        Tetromino newTetromino = new Tetromino(mino.getType(), mino.getRotation());
        return new GameField(
                arena,
                newTetromino,
                posH, posW
        );
    }

    /** Move the tetromino as far left as possible. */
    void moveToLeftBorder() {
        //replaced while with for loop and sanity check.
        for(int i = 0; i<TetrisArena.width && arena.checkTetromino(mino, posH, posW -1, true); i++)
            posW--;
    }
    /** Does not place the tetromino. */
    void moveToBottom() {
        //replaced while with for loop and sanity check.
        for(int i = 0; i<TetrisArena.height && arena.checkTetromino(mino, posH +1, posW, true); i++)
            posH++;
    }
    /** Does as if moveToBottom() was never invoked. */
    void resetToTop() {
        posH = 0;
    }
    /** Tries to move one to the right. */
    boolean moveRight() {
        boolean check = arena.checkTetromino(mino, posH, posW + 1, true);
        if (check)
            posW++;
        return check;
    }
    void rotateMino() {
        mino.rotate();
    }
    int getMinoRotationCount() {
        return mino.getType().length;
    }

    /** Getters **/

    int getPosH() {
        return posH;
    }
    int getPosW() {
        return posW;
    }

    @Override
    public String toString() {
        return arena.toString();
    }
}
