package at.dropical.wolliAI.types;
// Created by julian on 26.04.18.

import at.dropical.shared.PlayerAction;
import at.dropical.wolliAI.ServerAdapter;

/**
 * This moves the tetromino to the lowest
 * non-occupied column of the arena.
 */
public class LowestPointAI implements AI{

    private ServerAdapter serverAdapter;
    private int tick = 0;

    public LowestPointAI(ServerAdapter serverAdapter) {
        this.serverAdapter = serverAdapter;
    }

    @Override
    public void process() {
        // Make the AI slower
        if(tick % 5 == 0) {
            int column = findLowestColumn(serverAdapter.getArena());
            System.out.println(column);
            navigateToColumn(column, serverAdapter);
        }
        tick++;
    }

    /** @return the column to move to. */
    private int findLowestColumn(int[][] arena) {
        int lowestValue = Integer.MAX_VALUE;
        int lowestColumn = 0;

        // for all columns
        for(int i = 0; i < arena[0].length; i++) {
            int hight = findHighestBlock(arena, i);
            // find the minimum
            if(hight < lowestValue) {
                lowestValue = hight;
                lowestColumn = i;
            }
        }
        return lowestColumn;
    }

    /** @param column in what column to search.
     * @return the index of the highest block. */
    private int findHighestBlock(int[][] arena, int column) {
        boolean found = false;
        int i = arena.length-1;

        // Go down until a block was found.
        while(i>=0 && !found) {
            if(arena[i][column] != 0)
                found = true;
            i--;
        }
        return i;
    }

    /** Schaun ma moi hot da blinde gsogt. */
    private void navigateToColumn(int column, ServerAdapter server) {
        int direction = server.getXPos() - column;

        //Adjust to the tetromino
        boolean nothing = true;
        int[][] tetromino = server.getTetromino();

        for(int i = 0; i < tetromino.length; i++) {
            if(tetromino[i][0] != 0)
                nothing = false;
        }
        // when the left column of the tetromino is empty, it has to be adjusted.
        if(nothing)
            direction--;

        if(direction >0)
            server.sendInput(PlayerAction.LEFT);
        if(direction <0)
            server.sendInput(PlayerAction.RIGHT);

        server.sendInput(PlayerAction.DOWN);
    }
}
