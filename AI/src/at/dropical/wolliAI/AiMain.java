package at.dropical.wolliAI;
// Created by julian on 25.05.18.

import at.dropical.wolliAI.bestPossibility.BestPossibilityAI;
import at.dropical.wolliAI.types.AI;
import at.dropical.wolliAI.types.AlwaysLeftAI;
import at.dropical.wolliAI.types.TryToLoseAI;

/**
 * Starts the AI and connects to the default
 * localhost server.
 */
public class AiMain {
    /** Execute direktly */
    public static void main(String[] args) throws InterruptedException {
        new AiMain(new ServerAdapter()).loop();
    }
    /** Called from Server.
     * May be wise to execute in other thread. */
    public static void newAIconnection(String gameID) throws InterruptedException {
        new AiMain(new ServerAdapter(gameID)).loop();
    }


    private AI ai;

    /** The AI type is fixed. */
    public AiMain(ServerAdapter adapter) {
        ai = new BestPossibilityAI(adapter);
    }

    private void loop() throws InterruptedException{
        while(!Thread.currentThread().isInterrupted()) {
            Thread.sleep(100);
            ai.process();
        }
    }

    /* I have bugs in my code and they won't go,
     * Glitches in my code and they won't go. */
}
