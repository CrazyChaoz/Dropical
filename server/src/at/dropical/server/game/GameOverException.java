package at.dropical.server.game;

public class GameOverException extends Exception {
    private String looserName;

    public GameOverException(String looserName) {
        this.looserName = looserName;
    }

    public String getLooserName() {
        return looserName;
    }
}
