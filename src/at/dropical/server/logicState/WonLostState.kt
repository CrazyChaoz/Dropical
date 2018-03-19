package at.dropical.server.logicState

import at.dropical.server.gamefield.TetrisArena
import at.dropical.server.gamefield.Tetromino
import at.dropical.shared.GameState
import at.dropical.shared.PlayerAction

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
    override val state: GameState=GameState.GAME_OVER

    /** Forward the values from the runningState.
     *
     * Because that state object doesn't get updated
     * while this state is active, the values don't
     * change anymore.
     */
    override val tetrisArena: TetrisArena = runningState.tetrisArena
    override val tetromino: Tetromino = runningState.tetromino
    override val nextTetromino: Tetromino = runningState.nextTetromino
    override val h: Int = runningState.h
    override val w: Int = runningState.w

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