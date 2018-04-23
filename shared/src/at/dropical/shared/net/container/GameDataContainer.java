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
    private List<Integer> currTrockXs =new ArrayList<>();
    private List<Integer> currTrockYs =new ArrayList<>();
    private List<Integer> levels =new ArrayList<>();

    public GameDataContainer(GameState currentState) {
        super(currentState);
    }

    public List<int[][]> getCurrTrocks() {
        return currTrocks;
    }
    public List<Integer> getCurrTrockXs() {
        return currTrockXs;
    }
    public List<Integer> getCurrTrockYs() {
        return currTrockYs;
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
    public List<Integer> getLevels() {
        return levels;
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
        this.currTrockXs.add(X);
    }
    public void addCurrTrockY(Integer Y){
        this.currTrockXs.add(Y);
    }
    public void addPlayerName(String playerName){
        this.playernames.add(playerName);
    }
    public void addLevel(Integer level){
        this.levels.add(level);
    }
}
