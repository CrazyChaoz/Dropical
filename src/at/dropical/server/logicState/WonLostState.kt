package at.dropical.server.logicState

import at.dropical.server.gamefield.TetrisArena
import at.dropical.server.gamefield.Tetromino
import at.dropical.shared.fromHell.PlayerAction

// Created by julian on 01.03.18.
/**
 * Eventually one player will win or lose.
 * When this happens they should see the
 * mess they made in the tetrisArena,
 * but nothing should change anymore.
 *
 * And by pressing start the game resets.
 */
class WonLostState(
        val stateManager: StateManager,
        val runningState: RunningState
) : GameLogicState {

    /** Forward the values from the runningState.
     *
     * Because that state object doesn't get updated
     * while this state is active, the values don't
     * change anymore.
     */
    override val tetrisArena: TetrisArena
        get() = runningState.tetrisArena
    override val tetromino: Tetromino
        get() = runningState.tetromino
    override val nextTetromino: Tetromino
        get() = runningState.nextTetromino
    override val h: Int
        get() = runningState.h
    override val w: Int
        get() = runningState.w

    override fun run(goDown: Boolean, action: PlayerAction): Int {
        when (action) {
            PlayerAction.START -> stateManager.resetGame()
            PlayerAction.QUIT -> {/* ???????? */}
            else -> {}
        }
        return 0
    }

    override fun addLines(lines: Int) {
    }
}