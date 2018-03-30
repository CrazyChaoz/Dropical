package at.dropical.server.game;

public class SpecialGameOverException extends Exception {
    private String looserName;

    public SpecialGameOverException(String looserName) {
        this.looserName = looserName;
    }

    public String getLooserName() {
        return looserName;
    }
}
