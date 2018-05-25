package at.dropical.wolliAI.bestPossibility;
// Created by julian on 26.04.18.

import at.dropical.shared.PlayerAction;
import at.dropical.wolliAI.types.AI;
import at.dropical.wolliAI.serverAdapter.ServerAdapter;

/**
 * TODO Description
 * See Package-info.
 */
public class BestPossibilityAI implements AI {

    private final ServerAdapter server;
    private final BestPlaceFinder finder;
    private int tick = 0;

    public BestPossibilityAI(ServerAdapter server) {
        this.server = server;
        this.finder = new BestPlaceFinder();
    }

    @Override
    public void process() {
        // Make the AI slower
        if(tick % 10 == 0) {

            GameField field = new GameField(server.getArena(), server.getTetromino(), server.getXPos(), server.getYPos());
            BestPlaceFinder.BestPlace bestPlace = finder.findBestPlace(field);

            int direction = server.getYPos() - bestPlace.column;
            System.out.println(bestPlace);

            // rotate
            if(bestPlace.rotate >= 1)
                server.sendInput(PlayerAction.UP);
            // Go left or right.
            else if(direction > 0)
                server.sendInput(PlayerAction.LEFT);
            else if(direction < 0)
                server.sendInput(PlayerAction.RIGHT);
            else
                server.sendInput(PlayerAction.DOWN);

        }
        tick++;
    }
}
