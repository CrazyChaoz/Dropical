package at.dropical.server.game;

/** Is thrown when lines are cleared.
 * lines is therefore normally in the range of
 * 1 to 4. */
public class LinesClearedException extends Exception {
    private int lines;

    public LinesClearedException(int lines) {
        this.lines = lines;
    }

    public int getLines() {
        return lines;
    }
}
