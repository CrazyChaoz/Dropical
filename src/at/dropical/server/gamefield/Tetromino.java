package at.dropical.server.gamefield;
// Created by julian on 17.11.17.

import java.util.Random;

/**
 * This class represents the 4 blocks that come
 * down the screen.
 * The type and rotation is stored here.
 *
 * The different types:
 * I, T, L, J, Z, S, O
 */
public class Tetromino {
    public static final int size = 4;

    /** @see TetrominoTypes */
    private int[][][] type;
    /** Basically the index of type[] */
    private int rotation = 0;

    /** Returns a new Tetromino with a random
     * type and rotation. */
    public static Tetromino createRandom() {
        return new Tetromino(
                TetrominoTypes.random(),
                // Relies on the modulo in constructor.
                new Random().nextInt()
        );
    }

    /** Use one of {@link TetrominoTypes}.
     * Rotation value is allowed to be to high. */
    public Tetromino(int[][][] type, int rotation) {
        if(type[0].length != size || type[0][0].length != size)
            throw new IllegalArgumentException("Wrong Dimensions: " + type[0].length + "*" + type[0][0].length);
        this.type = type;
        this.rotation = Math.abs(rotation % type.length);
    }

    /**@return the 2D Array corresponding
     * to the specified type and the current
     * rotation. */
    public int[][] toArray() {
        return type[rotation];
    }

    /** Rotates the Tetromino in one direction. */
    public Tetromino rotate() {
        /* Cycle through all the rotations.
        * Every type can have a different amount
        * of possible rotations. (4, 2 or 1) */
        rotation = (rotation +1) % type.length;
        return this;
    }

    /** Rotates the Tetromino in the other direction. */
    public Tetromino rotateBack() {
        /* Decrement rotation while avoiding becoming negative. */
        rotation = (type.length + (rotation -1)) % type.length;
        return this;
    }

    public int getRotation() {
        return rotation;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int[][] type = toArray();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                builder.append(type[i][j]);
            }
            builder.append('\n');
        }

        return builder.toString();
    }
}
