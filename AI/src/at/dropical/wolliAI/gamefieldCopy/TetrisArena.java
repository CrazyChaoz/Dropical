package at.dropical.wolliAI.gamefieldCopy;
// Created by julian on 17.11.17.

import java.util.Random;

/**
 * The Game Arena is a 2D-Array that stores which
 * spaces (blocks) in the Tetris game are occupied
 * or empty.<br><br>
 *
 * Axes: arena[h][w]
 *  h=0 is top, h=height is the bottom of the arena.
 *  w=0 is left, w=width is right.
 *
 * The *internal* array is bigger than the actual
 * arena to make it easier doing checks with Tetrominos
 * without getting {@link ArrayIndexOutOfBoundsException}s.
 * The *actual* array is the arena the player sees.
 *
 * The array is int because the transmission protocol uses int.
 * The value 0 means there is no block,
 * the numbers are for the different types of Tetrominos/colors.
 */
public class TetrisArena {
    /** How big the standard arena is. */
    public static final int height = 20;
    public static final int width = 10;

    /** How much free space on the side is. */
    static final int marginLeftRight = 4;
    static final int marginBottom = 4;
    static final int marginTop = 4;
    /** The resulting internal dimensions. */
    static final int internalHeight = height + marginBottom + marginTop;
    static final int internalWidth = width + 2*marginLeftRight;

    /** Axis of the Arena: <br>
     *  Height is the first dimension and goes down,<br>
     *  Width the second and goes to the right.
     *  Therefore, (0|0) is top left. */
    private int[][] arena;

    /** Constructor for testing.
     * @param isActual lets you choose if the given arena has the internal
     * size or the actual size. */
    public TetrisArena(int[][] arena, boolean isActual) throws IllegalArgumentException {
        if(isActual) {
            fromArray(arena);
        } else {
            // Internal Array
            if(arena.length != internalHeight || arena[0].length != internalWidth)
                throw new IllegalArgumentException("Wrong Dimensions: " + arena.length + "*" + arena[0].length);

            this.arena = arena;
        }
    }

    /** Converts an actual arena to the internal arena. */
    private void fromArray(int[][] newArena) throws IllegalArgumentException {
        if(newArena.length != height || newArena[0].length != width)
            throw new IllegalArgumentException("Wrong Dimensions: "+ newArena.length +"*"+ newArena[0].length);

        this.arena = new int[TetrisArena.internalHeight][TetrisArena.internalWidth];
        for(int h = 0; h < height; h++) {
            // IntelliJ suggest that function. Is the overhead even worth it?
            System.arraycopy(newArena[h], 0, this.arena[h + marginTop], marginLeftRight, width);
        }
    }

    /** Checks if a Tetromino can be placed in a certain position.
     * @param h distance from the upper border of the actual arena.
     *          Can be negative.
     * @param w distance from the left border of the actual arena.
     * @param overTopAllowed should this return true when tetromino is out top?
     * @return false if it's out of bounds or if there are already
     * blocks at those positions. */
    public boolean checkTetromino(Tetromino tetromino, int h, int w, boolean overTopAllowed) {
        int[][] tetrominoArr = tetromino.toArray();
        for(int i = 0; i < Tetromino.size; i++) {
            for(int j = 0; j < Tetromino.size; j++) {
                // Only care if there is a block
                if(tetrominoArr[i][j] >= 1) {

                    // If space is occupied Or out of bounds of actual arena
                    if(arena[marginTop + h + i][marginLeftRight + w + j] >= 1
                            || w+j < 0    // Out left
                            || w+j >= width // Out right
                            || h+i >= height // Out bottom
                            || !overTopAllowed && (h+i <0))
                            /* Out top is sometimes allowed because the tetromino
                             * has to start falling down from over the top.*/

                        return false; //Stop if one block fails.
                }
            }
        }
        // Only when all 4*4 spaces of the Tetromino are ok.
        return true;
    }

    /** Only the actual arena. */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = marginTop; i < internalHeight-marginBottom; i++) {
            for(int j = marginLeftRight; j < internalWidth-marginLeftRight; j++) {
                builder.append(arena[i][j]);
            }
            builder.append('\n');
        }

        return builder.toString();
    }

    /** First find the lowest block of the tetromino
     * then find how many empty ones are under that.
     * Count them.
     *
     * @param posH posW The position of the tetromino in the arena.
     * @return the number of spaces that between the tetromino and
     * the existing blocks in the arena. */
    public int countSpacesBelowTetromino(Tetromino tetromino, int posH, int posW) {
        int count = 0;
        int[][] tetrominoArr = tetromino.toArray();
        // i=0 is the first column.
        // For every column
        for(int j = 0; j < Tetromino.size; j++) {
            //first find lowest block in the column
            int lowest = Integer.MAX_VALUE;
            for(int i = 0; i < Tetromino.size; i++) {
                if(tetrominoArr[i][j] >= 1)
                    lowest = i;
            }
            // At the end, the lowest occupied index is saved in lowest.

            if(lowest != Integer.MAX_VALUE) {
                // Second, see how many empty blocks are under that in the arena.
                int i = lowest+1;
                while(0 == arena[marginTop +posH +i][marginLeftRight +posW +j] && posH+i<height) {
                    i++;
                    count++;
                }
            }
        }
        return count;
    }
}
