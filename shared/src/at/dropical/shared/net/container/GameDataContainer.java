package at.dropical.shared.net.container;

import at.dropical.shared.GameState;
import at.dropical.shared.net.abstracts.Container;

import java.util.ArrayList;
import java.util.List;


public class GameDataContainer extends Container {
    private List<String> playernames=new ArrayList<>();
    private List<int[][]> arenas=new ArrayList<>();
    private List<int[][]> currTrocks=new ArrayList<>();
    private List<int[][]> nextTrocks =new ArrayList<>();
    private List<Integer> currTrockX=new ArrayList<>();
    private List<Integer> currTrockY =new ArrayList<>();

    private int level;

    public GameDataContainer(GameState currentState) {
        super(currentState);
    }

    public List<int[][]> getCurrTrocks() {
        return currTrocks;
    }

    public List<Integer> getCurrTrockX() {
        return currTrockX;
    }

    public List<Integer> getCurrTrockY() {
        return currTrockY;
    }

    public List<String> getPlayernames() {
        return playernames;
    }

    public List<int[][]> getArenas() {
        return arenas;
    }

    public List<int[][]> getNextTrocks() {
        return nextTrocks;
    }

    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        this.level = level;
    }


    public void addArena(int[][] arena) {
        this.arenas.add(arena);
    }
    public void addNextTrock(int[][] nextTrock) {
        this.nextTrocks.add(nextTrock);
    }
    public void addCurrTrock(int[][] currentTrock){
        this.currTrocks.add(currentTrock);
    }
    public void addCurrTrockX(Integer X){
        this.currTrockX.add(X);
    }
    public void addCurrTrockY(Integer Y){
        this.currTrockX.add(Y);
    }
    public void addPlayerName(String playerName){
        this.playernames.add(playerName);
    }



}
