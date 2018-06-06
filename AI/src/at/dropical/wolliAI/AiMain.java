package at.dropical.wolliAI;
// Created by julian on 25.05.18.

import at.dropical.wolliAI.bestPossibility.BestPossibilityAI;
import at.dropical.wolliAI.types.AI;

/**
 * Starts the AI and connects to the default
 * localhost server.
 */
public class AiMain {
    public static void main(String[] args) throws InterruptedException {
        AI ai = new BestPossibilityAI(new ServerAdapter());

        //Temp
        //AI ai2 = new BestPossibilityAI(new ServerAdapter());

        while(true) {
            Thread.sleep(100);
            try {
                ai.process();

            } catch(IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /* I have bugs in my code and they won't go,
     * Glitches in my code and they won't go. */
}
