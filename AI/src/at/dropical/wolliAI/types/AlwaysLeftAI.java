package at.dropical.wolliAI.types;// Created by julian on 25.04.18.

import at.dropical.shared.PlayerAction;
import at.dropical.wolliAI.serverAdapter.ServerAdapter;

/**
 * This is a simple test AI that always sends
 * PlayerAction.LEFT
 */
public class AlwaysLeftAI implements AI {

    ServerAdapter serverAdapter;

    public AlwaysLeftAI(ServerAdapter serverAdapter) {
        this.serverAdapter = serverAdapter;
    }

    public void process() {
        serverAdapter.sendInput(PlayerAction.LEFT);
        //serverAdapter.transmitData(); // In GameScreen
    }
}
