package at.dropical.server.game;

public class LinesClearedException extends Exception {
    private int lines;

    public LinesClearedException(int lines) {
        this.lines = lines;
    }

    public int getLines() {
        return lines;
    }
}
