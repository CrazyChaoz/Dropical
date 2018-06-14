package at.dropical.wolliAI.bestPossibility;
// Created by julian on 26.04.18.

/**
 * See Package-info.
 */
public class BestPlaceFinder {

    public BestPlace findBestPlace(GameField field) {
        return findBestRotation(field);
    }

    /**@return the best column with this rotation. */
    public BestPlace findBestRotation(GameField baseField) {
        GameField rotatingField = baseField.clone();
        BestPlace best = new BestPlace(0, 0, Integer.MAX_VALUE);

        // Get the rotation and column with the best score.
        for(int rotation = 0; rotation < rotatingField.getMinoRotationCount(); rotation++) {
            BestPlace current = findBestColumn(rotatingField);
            current.rotate = rotation;
            if(current.score < best.score)
                best = current;

            // Next rotation.
            rotatingField.rotateMino();
        }
        return best;
    }



    /** @return The posW of the column where the current
     * tetromino would leave the least empty spaces below
     * if placed. */
    public BestPlace findBestColumn(GameField field) {
        StringBuilder line = new StringBuilder(20);

        int bestColumnCount = Integer.MAX_VALUE;
        int bestColumn = 0;
        int bestColumnHeight = Integer.MAX_VALUE;
        // Begin left. Why not.
        field.moveToLeftBorder();

        do {
            field.moveToBottom();
            int below = field.countSpacesBelowTetromino();

            if(bestColumnCount > below
                    || bestColumnCount == below && bestColumnHeight < field.getPosH()) { //lowest point
                bestColumnCount = below;
                bestColumn = field.getPosW();
                bestColumnHeight = field.getPosH();
            }
            line.append(below);
            line.append(' ');

            field.resetToTop();
        } while(field.moveRight());

        //System.out.println(line);
        return new BestPlace(bestColumn, 0, bestColumnCount);
    }

    /** This just holds the two parameters that
     * are returned by findBestPlace(). */
    public class BestPlace {
        /** The posW position of the tetromino. */
        public int column;
        /** How often to rotate the tetromino. */
        public int rotate;
        /** I didn't want to make an extra class for this.*/
        public double score;

        public BestPlace(int column, int rotate, double score) {
            this.column = column;
            this.rotate = rotate;
            this.score = score;
        }

        @Override
        public String toString() {
            return "BestPlace{" +
                    "column=" + column +
                    ", rotate=" + rotate +
                    ", score=" + score +
                    '}';
        }
    }
}
