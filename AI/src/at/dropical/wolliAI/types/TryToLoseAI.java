package at.dropical.wolliAI.types;
// Created by julian on 12.06.18.

import at.dropical.shared.PlayerAction;
import at.dropical.wolliAI.ServerAdapter;

/**
 * Just drops to lose as fast as possible.
 */
public class TryToLoseAI implements AI {

    private ServerAdapter serverAdapter;

    public TryToLoseAI(ServerAdapter serverAdapter) {
        this.serverAdapter = serverAdapter;
    }

    public void process() {
        serverAdapter.sendInput(PlayerAction.DROP);
    }
}
