package at.dropical.shared.net.requests;

import at.dropical.shared.GameState;


public class GameDataContainer extends Container{
    private String[] playernames=new String[2];
    private int[][][] arenas=new int[2][20][10];
    private int[][][] nextTrock=new int[2][4][4];
    private int level;
    private int time;

    public GameDataContainer(GameState currentState) {
        super(currentState);
    }


    public String[] getPlayernames() {
        return playernames;
    }

    public int[][][] getArenas() {
        return arenas;
    }

    public int[][][] getNextTrock() {
        return nextTrock;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
