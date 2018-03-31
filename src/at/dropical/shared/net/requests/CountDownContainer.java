package at.dropical.shared.net.requests;

public class CountDownContainer {
    private String[] playernames;
    private int seconds;

    public CountDownContainer(String[] playernames, int seconds) {
        this.playernames = playernames;
        this.seconds = seconds;
    }

    public String[] getPlayernames() {
        return playernames;
    }

    public int getSeconds() {
        return seconds;
    }
}
