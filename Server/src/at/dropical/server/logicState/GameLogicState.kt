package at.dropical.server.logicState

import at.dropical.server.gamefield.TetrisArena
import at.dropical.server.gamefield.Tetromino
import at.dropical.shared.fromHell.PlayerAction

// Created by julian on 01.03.18.
/**
 * Interface for classes that process the game
 * according to what state ("mode") the game is in.
 * The game can be running (most important), paused, won or lost.
 *
 * Only the state that is currently active in SinglePlayer gets updated.
 * This is the idea behind pausing the game.
 *
 * This is a modified version of State Patterns, because
 * I fused the "user" and the "context" together into the
 * MultiPlayerManager.
 *
 * @see GameState
 * @see RunningState
 */
interface GameLogicState {

    val tetrisArena: TetrisArena
    /** Current Tetromino  */
    val tetromino: Tetromino
    /** The tetromino that will be used when the current fell down.  */
    val nextTetromino: Tetromino
    /** Distance from top. */
    val h: Int
    /** Distance from left. */
    val w: Int

    /** @return number of lines cleared. */
    fun run(goDown: Boolean, action: PlayerAction) :Int
    fun addLines(lines: Int)
}