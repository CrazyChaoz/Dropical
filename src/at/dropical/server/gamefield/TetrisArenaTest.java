//package at.dropical.server.gamefield;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//// Created by julian on 17.11.17.
//
///**
// * Süße-kleine-Kätzchen-Bilder
// * Süße, kleine Kätzchen-Bilder
// * Große Kleine-Kätzchen-Bilder
// */
//class TetrisArenaTest {
//
//    /** This reduces the amount of code in here because I don't have to
//     * write "Tetromino." all the time. */
//    private static final int height = TetrisArena.height;
//    private static final int width = TetrisArena.width;
//    /** How much free space on the side is. */
//    private static final int marginLeftRight = TetrisArena.marginLeftRight;
//    private static final int marginBottom = TetrisArena.marginBottom;
//    private static final int marginTop = TetrisArena.marginTop;
//    /** The resulting internal dimensions. */
//    private static final int internalHeight = TetrisArena.internalHeight;
//    private static final int internalWidth = TetrisArena.internalWidth;
//
//    /** Tests the exception that is thrown by the
//     * constructor and toArray. */
//    @Test
//    void testConstructor() {
//        // Test wrong size
//        boolean fehlgeschlagen = false;
//        try {
//            int[][] beides = new int[internalHeight][internalWidth-1];
//            new TetrisArena(beides, false);
//        } catch (IllegalArgumentException e) {
//            fehlgeschlagen = true;
//        }
//        Assertions.assertTrue(fehlgeschlagen);
//
//
//        // test with actual size. Also uses toArray()
//        int[][] arnea = new int[height][width];
//        for(int i = 0; i < height; i++) {
//            for(int j = 0; j < width; j++) {
//                arnea[i][j] = 100*i+j;
//            }
//        }
//        int[][] reconverted = new TetrisArena(arnea, true).toArray();
//        for(int i = 0; i < reconverted.length; i++) {
//            assertArrayEquals(reconverted[i], arnea[i]);
//        }
//    }
//
//    /** This test breaks if the internal margin chances... */
//    @Test
//    void toArrayTest() {
//        int[][] beides = new int[internalHeight][internalWidth];
//        for(int i = 0; i < internalHeight; i++) {
//            for(int j = 0; j < internalWidth; j++) {
//                beides[i][j] = (100*i+j);
//            }
//        }
//
//        StringBuilder s = new StringBuilder();
//        int[][] arena = new TetrisArena(beides, false).toArray();
//        for(int[] anArena : arena) {
//            for(int i : anArena) {
//                s.append(i).append("\t");
//            }
//            s.append('\n');
//        }
//        //System.out.println(s);
//        // I copied the sout output.
//        assertEquals(s.toString(), "404\t405\t406\t407\t408\t409\t410\t411\t412\t413\t\n" +
//                "504\t505\t506\t507\t508\t509\t510\t511\t512\t513\t\n" +
//                "604\t605\t606\t607\t608\t609\t610\t611\t612\t613\t\n" +
//                "704\t705\t706\t707\t708\t709\t710\t711\t712\t713\t\n" +
//                "804\t805\t806\t807\t808\t809\t810\t811\t812\t813\t\n" +
//                "904\t905\t906\t907\t908\t909\t910\t911\t912\t913\t\n" +
//                "1004\t1005\t1006\t1007\t1008\t1009\t1010\t1011\t1012\t1013\t\n" +
//                "1104\t1105\t1106\t1107\t1108\t1109\t1110\t1111\t1112\t1113\t\n" +
//                "1204\t1205\t1206\t1207\t1208\t1209\t1210\t1211\t1212\t1213\t\n" +
//                "1304\t1305\t1306\t1307\t1308\t1309\t1310\t1311\t1312\t1313\t\n" +
//                "1404\t1405\t1406\t1407\t1408\t1409\t1410\t1411\t1412\t1413\t\n" +
//                "1504\t1505\t1506\t1507\t1508\t1509\t1510\t1511\t1512\t1513\t\n" +
//                "1604\t1605\t1606\t1607\t1608\t1609\t1610\t1611\t1612\t1613\t\n" +
//                "1704\t1705\t1706\t1707\t1708\t1709\t1710\t1711\t1712\t1713\t\n" +
//                "1804\t1805\t1806\t1807\t1808\t1809\t1810\t1811\t1812\t1813\t\n" +
//                "1904\t1905\t1906\t1907\t1908\t1909\t1910\t1911\t1912\t1913\t\n" +
//                "2004\t2005\t2006\t2007\t2008\t2009\t2010\t2011\t2012\t2013\t\n" +
//                "2104\t2105\t2106\t2107\t2108\t2109\t2110\t2111\t2112\t2113\t\n" +
//                "2204\t2205\t2206\t2207\t2208\t2209\t2210\t2211\t2212\t2213\t\n" +
//                "2304\t2305\t2306\t2307\t2308\t2309\t2310\t2311\t2312\t2313\t\n");
//    }
//
//    @Test
//    void checkTetrominoSomeTest() {
//        int[][] arena = new int[height][width];
//        // The 6th vertical column is used.
//        for(int i = 0; i < height; i++) {
//            arena[i][5] = 3;
//        }
//        TetrisArena tetrisArena = new TetrisArena(arena, true);
//
//        Tetromino tetromino = new Tetromino(TetrominoTypes.Jmatrix, 1);
//        assertTrue(tetrisArena.checkTetromino(tetromino, 0, 0, true));
//        // Over the top, allowed and not allowed.
//        assertTrue(tetrisArena.checkTetromino(tetromino, -marginTop, 4, true));
//        assertFalse(tetrisArena.checkTetromino(tetromino, -marginTop, 4, false));
//        // Collide with column
//        assertFalse(tetrisArena.checkTetromino(tetromino, 5, 4, true));
//        // Under bottom
//        assertFalse(tetrisArena.checkTetromino(tetromino, height, 4, true));
//        // bottom most line
//        assertTrue(tetrisArena.checkTetromino(tetromino, height - 3, 0, true));
//    }
//
//    /** Not finished.
//     * Check the entire field, block for block,
//     * if checkTetromino works. */
//    @Test
//    void checkTetrominoSystematicEverythingTest() {
//        /* **** Check the entire field if it checks correctly. */
//        TetrisArena tetrAren2 = new TetrisArena();
//        Tetromino mino = new Tetromino(TetrominoTest.dot, Integer.MIN_VALUE /* Because I can! */);
//
//        // The normal area should work.
//        for(int h = 0; h < height; h++) {
//            for(int w = 0; w < width; w++) {
//                assertTrue(tetrAren2.checkTetromino(mino, h, w, false));
//            }
//        }
//        // Under the bottom shouldn't work. (This helped me fix a bug.)
//        int h = height;
//        for(int w = -marginLeftRight; w < (width + marginLeftRight); w++) {
//            assertFalse(tetrAren2.checkTetromino(mino, h, w, false), "h=" + h + ",w=" + w);
//        }
//    }
//
//    @Test
//    void placeTetrominoTest() {
//        // set up the arena
//        int[][] arena = new int[height][width];
//        // A vertical column is used.
//        for(int i = 0; i < height; i++) {
//            arena[i][5] = 3;
//        }
//        TetrisArena tetrisArena = new TetrisArena(arena, true);
//
//        Tetromino tetrominoVertical = new Tetromino(TetrominoTypes.Imatrix, 1);
//        Tetromino tetrominoHorizontal = new Tetromino(TetrominoTypes.Imatrix, 0);
//
//        assertTrue(tetrisArena.placeTetromino(tetrominoHorizontal, -1, 0));
//        // Now it is obscured
//        assertFalse(tetrisArena.placeTetromino(tetrominoVertical, 0, 0));
//        assertTrue(tetrisArena.placeTetromino(tetrominoVertical, 3, -1));
//        // Collide with the column
//        assertFalse(tetrisArena.placeTetromino(tetrominoHorizontal, 8, 4));
//        // Over the top
//        assertFalse(tetrisArena.placeTetromino(tetrominoHorizontal, -2, 1));
//
//        // As low as possible
//        assertTrue(tetrisArena.placeTetromino(tetrominoHorizontal, 18, 0));
//        // One to low
//        assertFalse(tetrisArena.placeTetromino(tetrominoHorizontal, 19, 0));
//    }
//
//
//    @Test
//    void placeTetrominoGameSimulation() {
//        TetrisArena tetrisArena = new TetrisArena();
//        Tetromino upwardsT = new Tetromino(TetrominoTypes.Tmatrix, 2);
//        Tetromino verticalS = new Tetromino(TetrominoTypes.Smatrix, 1);
//
//        // Place under and at the bottom
//        assertFalse(tetrisArena.placeTetromino(upwardsT, 19, 0));
//        assertTrue(tetrisArena.placeTetromino(upwardsT, 18, 0));
//
//        // Place on the top (right) of the previous.
//        assertFalse(tetrisArena.placeTetromino(verticalS, 17, 0));
//        assertTrue(tetrisArena.placeTetromino(verticalS, 16, 0));
//
//        breakpoint();
//    }
//
//    @Test
//    void clearLinesTest() {
//        int[][] arena = new int[height][width];
//        // Fill some horizontal lines.
//        for(int j = 0; j < width; j++) {
//            arena[0][j] = 3;
//            arena[height-1][j] = 3;
//            arena[3][j] = 3;
//            arena[5][j] = 4;
//            arena[6][j] = (int) Math.round(Math.random()*8) +1;
//
//            // Don't fill these
//            arena[2][j] = 3;
//            arena[7][j] = 1;
//        }
//        // Don't fill these
//        arena[2][0] = 0;
//        arena[2][6] = 0;
//        arena[7][width-1] = 0;
//
//        // I have filled 5 lines.
//        TetrisArena tetrisArena = new TetrisArena(arena, true);
//        System.out.println(tetrisArena.toString());
//        assertEquals(5, tetrisArena.clearLines());
//
//        //System.out.println(tetrisArena.toString());
//        assertEquals("0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0333330333\n" +
//                "0000000000\n" +
//                "1111111110\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n" +
//                "0000000000\n",
//                tetrisArena.toString());
//
//        // It has already be cleared, so it shouldn't clear any more.
//        assertEquals(0, tetrisArena.clearLines());
//    }
//
//    @Test
//    void addLinesTest() {
//        TetrisArena arena = new TetrisArena();
//        assertFalse(arena.addLines(1));
//        assertFalse(arena.addLines(height -1));
//
//        //Now over top, because nothing cleared
//        assertEquals(0, arena.clearLines());
//        assertTrue(arena.addLines(2));
//        // Shouldn't trow exception
//        assertTrue(arena.addLines(4));
//    }
//
//    /** Manual Test. */
//    //@Test @Disabled
//    void toStringTest() {
//        int[][] beides = new int[internalHeight][internalWidth];
//        for(int i = 0; i < internalHeight; i++) {
//            for(int j = 0; j < internalWidth; j++) {
//                beides[i][j] = (i+j)%10;
//            }
//        }
//        System.out.println(new TetrisArena(beides, false).toString());
//    }
//
//    void breakpoint() {}
//}