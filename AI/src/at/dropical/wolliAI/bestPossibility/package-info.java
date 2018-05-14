// Created by julian on 26.04.18.

/** <h1>Brute Force</h1>
 * This AI uses brute force to calculate
 * the best possibility where to place
 * the tetromino.
 * It assumes that it doesn't make mistakes/holes
 * and always goes from the top downwards.
 *
 * <h1>Levels</h1>
 * There are many levels of detail of the
 * things the AI can consider.
 *
 * Level 1: What is the best place for the
 *    current tetromino in the current rotation?
 * Level 2: Which one of the possible rotations of the current
 *    tetromino is the best?
 * Level 3: For all of those possibilities, how
 *    good can the next tetromino be placed?
 *    (Is there a good place for it?)
 * Level 4: How are the chances for all possible
 *    tetrominos after that?
 *    (Is there a good place for all of them?)
 * Level n: recursively do Level 4. The calculation
 *    power needed will get ridiculous.
 *
 * <h1>What means 'best place'?</h1>
 * When no empty spaces will remain under the currently
 * placed tetromino, that is the best.
 *
 */
package at.dropical.wolliAI.bestPossibility;