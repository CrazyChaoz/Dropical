package at.dropical.wolliAI.gamefieldCopy;
// Created by julian on 17.11.17.

import java.util.Random;

/**
 * The types in the ascending order:
 * I, T, L, J, Z, S, O
 * For selecting a random one, use random().
 * Additionally there is an empty type.
 *
 * The Tetrominos are an Array of the different
 * rotations of one Tetromino.<br>
 * <code>
 *     int[][] rotatedTetr = Imatrix[0]<br>
 * </code>
 * rotatedTetr has the size {@link Tetromino#size}<br>
 *
 * I am not pleased with how this is implemented,
 * but I didn't know how to do this with enums.
 * The autoformatting of IntelliJ is messing
 * up here a little bit.
 */
public class TetrominoTypes {

    //IIIIIIIIIIIIIIIIIIIIIIIIIIII
    public static final int[][][] Imatrix = {
            {
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            }, {
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0}
    }};
    //TTTTTTTTTTTTTTTTTTTTTTTTTTTT
    public static final int[][][] Tmatrix = {
            {
                    {0, 0, 0, 0},
                    {2, 2, 2, 0},
                    {0, 2, 0, 0},
                    {0, 0, 0, 0}
            }, {
            {0, 2, 0, 0},
            {2, 2, 0, 0},
            {0, 2, 0, 0},
            {0, 0, 0, 0}
    }, {
            {0, 2, 0, 0},
            {2, 2, 2, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    }, {
            {0, 2, 0, 0},
            {0, 2, 2, 0},
            {0, 2, 0, 0},
            {0, 0, 0, 0}
    }};
    //LLLLLLLLLLLLLLLLLLLLLLLLLLLL
    public static final int[][][] Lmatrix = {
            {
                    {0, 0, 0, 0},
                    {3, 3, 3, 0},
                    {3, 0, 0, 0},
                    {0, 0, 0, 0}
            }, {
            {3, 3, 0, 0},
            {0, 3, 0, 0},
            {0, 3, 0, 0},
            {0, 0, 0, 0}
    }, {
            {0, 0, 3, 0},
            {3, 3, 3, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    }, {
            {0, 3, 0, 0},
            {0, 3, 0, 0},
            {0, 3, 3, 0},
            {0, 0, 0, 0}
    }};
    //JJJJJJJJJJJJJJJJJJJJJJJJJJJJ
    public static final int[][][] Jmatrix = {
            {
                    {4, 0, 0, 0},
                    {4, 4, 4, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            }, {
            {0, 4, 4, 0},
            {0, 4, 0, 0},
            {0, 4, 0, 0},
            {0, 0, 0, 0}
    }, {
            {0, 0, 0, 0},
            {4, 4, 4, 0},
            {0, 0, 4, 0},
            {0, 0, 0, 0}
    }, {
            {0, 4, 0, 0},
            {0, 4, 0, 0},
            {4, 4, 0, 0},
            {0, 0, 0, 0}
    }};
    //ZZZZZZZZZZZZZZZZZZZZZZZZZZZZ
    public static final int[][][] Zmatrix = {
            {
                    {0, 0, 0, 0},
                    {5, 5, 0, 0},
                    {0, 5, 5, 0},
                    {0, 0, 0, 0}
            }, {
            {0, 0, 5, 0},
            {0, 5, 5, 0},
            {0, 5, 0, 0},
            {0, 0, 0, 0}
    }};
    //SSSSSSSSSSSSSSSSSSSSSSSSSSSS
    public static final int[][][] Smatrix = {
            {
                    {0, 0, 0, 0},
                    {0, 6, 6, 0},
                    {6, 6, 0, 0},
                    {0, 0, 0, 0}
            }, {
            {0, 6, 0, 0},
            {0, 6, 6, 0},
            {0, 0, 6, 0},
            {0, 0, 0, 0}
    }};
    //OOOOOOOOOOOOOOOOOOOOOOOOOOOO
    public static final int[][][] Omatrix = {
            {
                    {0, 7, 7, 0},
                    {0, 7, 7, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
    }};


    public static final int[][][] emptyMatrix = {
            {
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            }};

    public static int[][][][] everything = new int[][][][]{
            Imatrix, Tmatrix, Lmatrix, Jmatrix, Zmatrix, Smatrix, Omatrix};

    /** How many types there are. Not counting an empty one.
     * Has to be updated manually if code changes! */
    public static final int count = everything.length;
}
