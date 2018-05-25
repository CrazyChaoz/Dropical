package at.dropical.wolliAI;
// Created by julian on 25.05.18.

import at.dropical.wolliAI.bestPossibility.BestPossibilityAI;
import at.dropical.wolliAI.serverAdapter.ServerAdapter;
import at.dropical.wolliAI.types.AI;

/**
 * Starts the AI and connects to the default
 * localhost server.
 */
public class AiMain {
    public static void main(String[] args) throws InterruptedException {
        AI ai = new BestPossibilityAI(new ServerAdapter());

        while(true) {
            System.out.println("Schleife");
            Thread.sleep(100);
            System.out.println("Geschlafen");
            ai.process();
            System.out.println("Prozessiert");
        }
    }

    /* I have bugs in my code and they won't go,
     * Glitches in my code and they won't go. */
}
