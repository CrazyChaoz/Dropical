//package at.dropical.server.gamefield;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//// Created by julian on 17.11.17.
//
///**
// * Tests if the it rotates properly.
// */
//class TetrominoTest {
//
//    /** Checks if the axies of the Type are
//     * not reversed. */
//    @Test
//    void tetrominoToStringTest()  {
//        // The horizontal I
//        Tetromino tetromino = new Tetromino(TetrominoTypes.Imatrix, 0);
//        //System.out.println(tetromino.toString());
//        Assertions.assertEquals("0000\n" +
//                "1111\n" +
//                "0000\n" +
//                "0000\n",
//                tetromino.toString());
//    }
//
//    @Test
//    void rotateAndConstructorTest() {
//        // This one has only one rotation
//        Tetromino tetromino1 = new Tetromino(TetrominoTypes.Omatrix, 0);
//        tetromino1.rotate();
//        Assertions.assertEquals(0, tetromino1.getRotation());
//
//        Tetromino tetromino2 = new Tetromino(TetrominoTypes.Tmatrix, 2);
//        tetromino2.rotate();
//        tetromino2.rotate();
//        Assertions.assertEquals(0, tetromino2.getRotation());
//
//        // rotation is to high
//        Tetromino tetromino3 = new Tetromino(TetrominoTypes.Imatrix, 2);
//        Assertions.assertEquals(0, tetromino3.getRotation());
//
//        // rotation is negative
//        Tetromino tetromino4 = new Tetromino(TetrominoTypes.Omatrix, Integer.MIN_VALUE);
//        Assertions.assertEquals(0, tetromino4.getRotation());
//        Tetromino tetromino5 = new Tetromino(TetrominoTypes.Omatrix, -43);
//        Assertions.assertEquals(0, tetromino4.getRotation());
//    }
//
//    /** This test relies on randomness. That's not
//     * a good idea, isn't it? */
//    @Test
//    void createRandomTest() {
//        // Rotation should always be valid.
//        // (No type has more than four rotations.)
//        for(int i = 0; i < 10; i++) {
//            Tetromino tetromino = Tetromino.createRandom();
//            Assertions.assertTrue(tetromino.getRotation() < 4);
//        }
//    }
//
//    @Test
//    void rotateBackTest() {
//        // Underflow
//        Tetromino tetr = new Tetromino(TetrominoTypes.Tmatrix, 0);
//        tetr.rotateBack();
//        tetr.rotateBack();
//        Assertions.assertEquals(2, tetr.getRotation());
//
//        // forwards and backwards
//        tetr.rotateBack();
//        tetr.rotate();
//        Assertions.assertEquals(2, tetr.getRotation());
//
//        // forwards and backwards with overflow
//        Tetromino tetromino42 = new Tetromino(TetrominoTypes.Imatrix, 1);
//        tetromino42.rotate();
//        tetromino42.rotateBack();
//        Assertions.assertEquals(1, tetromino42.getRotation());
//    }
//
//
//    /* ******** Custom Tetromino Types for Testing only ******/
//    static final int[][][] dot = {
//            {
//                    {42, 0, 0, 0},
//                    {0, 0, 0, 0},
//                    {0, 0, 0, 0},
//                    {0, 0, 0, 0}
//            }};
//}