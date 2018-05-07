package at.dropical.client.impl;

import at.dropical.client.DropicalListener;
import at.dropical.client.DropicalProxy;
import at.dropical.shared.PlayerAction;
import at.dropical.shared.net.container.CountDownContainer;
import at.dropical.shared.net.container.GameDataContainer;
import at.dropical.shared.net.container.GameOverContainer;
import at.dropical.shared.net.container.ListDataContainer;
import at.dropical.shared.net.requests.HandleInputRequest;
import at.dropical.shared.net.requests.JoinRequest;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Kemps_TestAI implements DropicalListener {

    public static void main(String[] args) throws IOException {
        new Kemps_TestAI();
    }

    public static Kemps_TestAI instance = null;

    public int myNum = 0;
    public String myName = "TestAI";

    public DropicalProxy proxy = null;

    public Lock lock = new ReentrantLock();
    //    private ExecutorService threadies = Executors.newCachedThreadPool(); //rows*colums*rotations
    public TreeSet<Position> possiblePosition = new TreeSet<>();


    public int[][] currTrock;
    public int currX;
    public int currY;

    public Kemps_TestAI() throws IOException {

        if (instance != null)
            return;

        instance = this;

        proxy = new DropicalProxy("localhost", 45000, this);

        proxy.writeToServer(new JoinRequest(myName));
    }

    @Override
    public void countDown(CountDownContainer container) {
        // No need
        System.out.println("Doing silly warmup moves");
        myNum = 0;
        for (String s : container.getPlayernames()) {
            if (s.equals(myName)) {
                return;
            }
            myNum++;
        }
    }

    @Override
    public void updateUI(GameDataContainer container) {
        // calculate next step

        System.out.println("gamedatacontainer received");


        currX = container.getCurrTrockXs().get(myNum);
        currY = container.getCurrTrockYs().get(myNum);
        currTrock = container.getCurrTrocks().get(myNum);


        try {
            instance.doCalc(container);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void somebodyJoinedTheLobby(ListDataContainer container) {
        // No need
    }

    @Override
    public void onGameOver(GameOverContainer container) {
        System.out.println("Bot says: game over");
    }

    public void doCalc(GameDataContainer container) throws InterruptedException {
        new CalcThread(container);
    }

    public int[][] rotateByNinetyToLeft(int[][] currTrock) {

        int[][] m = new int[4][4];
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                m[y][x] = currTrock[y][x];
            }
        }
        int e = m.length - 1;
        int c = e / 2;
        int b = e % 2;
        for (int r = c; r >= 0; r--) {
            for (int d = c - r; d < c + r + b; d++) {
                int t = m[c - r][d];
                m[c - r][d] = currTrock[d][e - c + r];
                m[d][e - c + r] = m[e - c + r][e - d];
                m[e - c + r][e - d] = m[e - d][c - r];
                m[e - d][c - r] = t;
            }
        }
        return m;
    }

}


class CalcThread extends Thread {
    private List<PlayerAction> movelist = new ArrayList<>();
    private GameDataContainer container;
    private int currY;

    public CalcThread(GameDataContainer container) {
        this.container = container;
        this.start();
    }

    @Override
    public void run() {
        if (!Kemps_TestAI.instance.lock.tryLock())
            return;


        currY=container.getCurrTrockYs().get(Kemps_TestAI.instance.myNum);



        Kemps_TestAI.instance.possiblePosition.clear();


        for (int y = 0; y < 20; y++) {
            new ColCalcNoThreaded(container.getArenas().get(Kemps_TestAI.instance.myNum), container.getCurrTrocks().get(Kemps_TestAI.instance.myNum), y);
            new ColCalcNoThreaded(container.getArenas().get(Kemps_TestAI.instance.myNum), Kemps_TestAI.instance.rotateByNinetyToLeft(container.getCurrTrocks().get(Kemps_TestAI.instance.myNum)), y);
            new ColCalcNoThreaded(container.getArenas().get(Kemps_TestAI.instance.myNum), Kemps_TestAI.instance.rotateByNinetyToLeft(Kemps_TestAI.instance.rotateByNinetyToLeft(container.getCurrTrocks().get(Kemps_TestAI.instance.myNum))), y);
            new ColCalcNoThreaded(container.getArenas().get(Kemps_TestAI.instance.myNum), Kemps_TestAI.instance.rotateByNinetyToLeft(Kemps_TestAI.instance.rotateByNinetyToLeft(Kemps_TestAI.instance.rotateByNinetyToLeft(container.getCurrTrocks().get(Kemps_TestAI.instance.myNum)))), y);
        }

//        threadies.awaitTermination(60, TimeUnit.SECONDS);

//        System.out.println("########");
//        for (Position position : possiblePosition) {
//            System.out.println(position);
//        }
//        System.out.println("########");


        for (Position position : Kemps_TestAI.instance.possiblePosition) {
            System.out.println(position);
            TrialIterator iterator = new TrialIterator(container.getArenas().get(Kemps_TestAI.instance.myNum), position.getTrock(), position.getX(), position.getY());
            if (iterator.isReachable()) {
                movelist = iterator.getMovelist();
                break;
            }
        }


        System.out.println("sending to server");
        for (PlayerAction playerAction : movelist) {

//            if(currY>Kemps_TestAI.instance.currY){
//                currY--;
//                continue;
//            }

            System.out.println("Move: " + playerAction.name());
            Kemps_TestAI.instance.proxy.writeToServer(new HandleInputRequest(Kemps_TestAI.instance.myName, playerAction));
        }
//        Kemps_TestAI.instance.proxy.writeToServer(new HandleInputRequest(Kemps_TestAI.instance.myName, PlayerAction.DOWN));

//        Thread.sleep(500);


        Kemps_TestAI.instance.lock.unlock();
    }
}


class TrialIterator {

    private List<PlayerAction> movelist = new ArrayList<>();
    private boolean isReachable;


    public TrialIterator(int[][] arena, int[][] currTrock, int currX, int currY) {
        isReachable = iterateHigher(arena, currTrock, currX, currY, null, 0);
    }

    public boolean isReachable() {
        return isReachable;
    }

    public List<PlayerAction> getMovelist() {
        return movelist;
    }

    public boolean iterateHigher(int[][] arena, int[][] currTrock, int currX, int currY, PlayerAction lastMove, int timesExecuted) {

        if (currY < Kemps_TestAI.instance.currY)
            return false;

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (currTrock[y][x] != 0) {
                    if (currX + x >= 10 || currY + y >= 20 || currX + x < 0 || currY + y < 0) {
                        return false;
                    }
                    if (arena[currY + y][currX + x] != 0)
                        return false;
                }
            }
        }


        //positive break condition
        if (currX == Kemps_TestAI.instance.currX && currY == Kemps_TestAI.instance.currY) {
            boolean positive = true;
            for (int y = 0; y < 4; y++)
                for (int x = 0; x < 4; x++)
                    if (currTrock[y][x] != Kemps_TestAI.instance.currTrock[y][x])
                        positive = false;

            if (positive) {
                System.out.println("positive condition reached");
                return true;
            }
        }


        if (iterateHigher(arena, currTrock, currX, currY - 1, PlayerAction.DOWN, 1)) {
            movelist.add(PlayerAction.DOWN);
            return true;
        }

        if (lastMove != PlayerAction.LEFT && timesExecuted <= 30) {
            if (iterateHigher(arena, currTrock, currX - 1, currY, PlayerAction.RIGHT, (lastMove != PlayerAction.RIGHT && lastMove != PlayerAction.UP) ? 1 : timesExecuted + 1)) {
                movelist.add(PlayerAction.RIGHT);
                return true;
            }
        }

        if (lastMove != PlayerAction.RIGHT && timesExecuted <= 30) {
            if (iterateHigher(arena, currTrock, currX + 1, currY, PlayerAction.LEFT, (lastMove != PlayerAction.LEFT && lastMove != PlayerAction.UP) ? 1 : timesExecuted + 1)) {
                movelist.add(PlayerAction.LEFT);
                return true;
            }
        }
        if (lastMove != PlayerAction.UP || timesExecuted <= 30) {
            if (iterateHigher(arena, Kemps_TestAI.instance.rotateByNinetyToLeft(currTrock), currX, currY, PlayerAction.UP, timesExecuted + 1)) {
                movelist.add(PlayerAction.UP);
                return true;
            }
        }

        return false;
    }


}

class Position implements Comparable<Position> {
    private int weight;
    private int[][] trock;
    private int x;
    private int y;

    public Position(int[][] trock, int y, int x, int weight) {
        this.weight = weight;
        this.trock = trock;
        this.x = x;
        this.y = y;
    }

    public int getWeight() {
        return weight;
    }

    public int[][] getTrock() {
        return trock;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "\nweight=" + weight +
                "\nx=" + x +
                "\ny=" + y +
                "\n}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return weight == position.weight &&
                x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, x, y);
    }

    @Override
    public int compareTo(@NotNull Position o) {
        return o.getWeight() != this.getWeight() ?
                o.getWeight() - this.getWeight() : o.getY() != this.getY() ?
                o.getY() - this.getY() : o.getX() - this.getX();
    }
}


class ColCalcNoThreaded {

    private int[][] arena;
    private int[][] currTrock;
    private int currY;


    public ColCalcNoThreaded(int[][] arena, int[][] currTrock, int currY) {
        this.arena = arena;
        this.currTrock = currTrock;
        this.currY = currY;

        for (int currX = 0; currX < 10; currX++) {
            if (tryCalc(currX)) {
                Kemps_TestAI.instance.possiblePosition.add(new Position(currTrock, currY, currX, calculateWeight(currX)));
            }
        }
    }


    public boolean tryCalc(int currX) {
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (currTrock[y][x] != 0) {
                    if (currX + x >= 10 || currY + y >= 20) {
                        //this branch has failed
                        return false;
                    }
                    if (arena[currY + y][currX + x] != 0)
                        return false;
                }
            }
        }
        return true;
    }

    public int calculateWeight(int currX) {
        int weight = calculatePositionBasedWeight()/10;

        weight += calculateSurroundingBlocks(currX);
//        weight += calculateEmptyBlocksBeneith()/10;

        return weight;
    }

    public int calculateSurroundingBlocks(int currX) {
        int weight = 0;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (currTrock[y][x] != 0) {
                    //lookup every surrounding block

                    if (currX + x - 1 >= 0) {
                        if (arena[currY + y][currX + x - 1] != 0)       //a block in the arena is to the left
                            weight += 2;
                    } else {
                        weight += 3;                                    //border left
                    }

                    if (currX + x + 1 < 10) {
                        if (arena[currY + y][currX + x + 1] != 0)       //a block in the arena is to the right
                            weight += 2;
                    } else {
                        weight += 3;                                    //border right
                    }

                    if (currY + y - 1 >= 0)
                        if (arena[currY + y - 1][currX + x] != 0)       //a block in the arena is on top
                            weight += 30;


                    if (currY + y + 1 < 20) {
                        if (arena[currY + y + 1][currX + x] != 0)       //a block in the arena is underneath
                            weight += 1;
                    } else {
                        weight += 10;                                    //border bottom
                    }

                }
            }
        }
        return weight;
    }

    public int calculatePositionBasedWeight(){
        int weight=0;
        for (int y = 0; y < 4; y++)
            for (int x = 0; x < 4; x++)
                if (currTrock[y][x] != 0)
                    weight+=currY+y;
        return weight;
    }
    public int calculateEmptyBlocksBeneith() {
        int weight = 0;
        for (int y = currY + 4; y < 20; y++) {
            for (int x = 0; x < 10; x++) {
                if (arena[y][x] == 0)
                    weight -= 1;
            }
        }

        return weight;
    }
}


//class ColumnCalculator implements Runnable {
//
//    private int[][] arena;
//    private int[][] currTrock;
//    private int currY;
//
//
//    public ColumnCalculator(int[][] arena, int[][] currTrock, int currY) {
//        this.arena = arena;
//        this.currTrock = currTrock;
//        this.currY = currY;
//    }
//
//    @Override
//    public void run() {
//        for (int currX = 0; currX < 10; currX++) {
//            if (tryCalc(currX)) {
//                Kemps_TestAI.instance.possiblePosition.add(new Position(currTrock, currY, currX, calculateWeight(currX)));
//            }
//        }
//    }
//
//    public boolean tryCalc(int currX) {
//        for (int x = 0; x < 4; x++) {
//            for (int y = 0; y < 4; y++) {
//                if (currTrock[y][x] != 0) {
//                    if (currX + x >= 10 || currY + y >= 20) {
//                        //this branch has failed
//                        return false;
//                    }
//                    if (arena[currY + y][currX + x] != 0)
//                        return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public int calculateWeight(int currX) {
//        int weight = currY;
//
//        for (int y = 0; y < 4; y++) {
//            for (int x = 0; x < 4; x++) {
//                if (currTrock[y][x] != 0) {
//                    //lookup every surrounding block
//
//                    if (currX + x - 1 >= 0) {
//                        if (arena[currY + y][currX + x - 1] != 0)       //a block in the arena is to the left
//                            weight += 1;
//                    } else
//                        weight += 1;                                    //border left
//
//
//                    if (currX + x + 1 < 10) {
//                        if (arena[currY + y][currX + x + 1] != 0)       //a block in the arena is to the right
//                            weight += 1;
//                    } else
//                        weight += 1;                                    //border right
//
//
//                    if (currY + y - 1 >= 0) {
//                        if (arena[currY + y - 1][currX + x] != 0)       //a block in the arena is on top
//                            weight += 1;
//                    } else
//                        weight += 1;                                    //border top
//
//
//                    if (currY + y + 1 < 20) {
//                        if (arena[currY + y + 1][currX + x] != 0)       //a block in the arena is underneath
//                            weight += 1;
//                    } else
//                        weight += 1;                                    //border bottom
//
//
//                }
//            }
//        }
//
//        return weight;
//    }
//}