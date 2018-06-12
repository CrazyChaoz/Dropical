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
    public static void main(String[] args) throws InterruptedException {
        AI ai = new BestPossibilityAI(new ServerAdapter());

        //Temp to occupy full game
        //AI ai2 = new TryToLoseAI(new ServerAdapter());

        while(true) {
            Thread.sleep(100);
            try {
                ai.process();
                //ai2.process();

            } catch(IndexOutOfBoundsException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /* I have bugs in my code and they won't go,
     * Glitches in my code and they won't go. */
}
