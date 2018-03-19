package at.dropical.server.logicState

import at.dropical.server.gamefield.TetrisArena
import at.dropical.server.gamefield.Tetromino
import at.dropical.server.gamefield.TetrominoTypes
import at.dropical.shared.GameState
import at.dropical.shared.PlayerAction

// Created by julian on 01.03.18.
/**
 * When the game is paused, the player sees an empty arena.
 */
class PausedState(
        val stateManager: StateManager
) : GameLogicState {

    override val tetrisArena: TetrisArena = TetrisArena()
    override val tetromino: Tetromino = Tetromino(TetrominoTypes.emptyMatrix, 0)
    override val nextTetromino: Tetromino = Tetromino(TetrominoTypes.emptyMatrix, 0)
    override val h: Int = 0
    override val w: Int = 0

    /** Does nothing besides changing the game state back to running.
     * TODO Countdown when resuming */
    override fun run(goDown: Boolean, action: PlayerAction): Int {
        when (action) {
            PlayerAction.START -> stateManager.changeGameState(GameState.GAME_RUNNING)
            PlayerAction.QUIT -> stateManager.changeGameState(GameState.GAME_LOST)
            else -> {}
        }
        return 0
    }

    override fun addLines(lines: Int) {
        throw IllegalStateException("addLines only possible when in running state.")
    }

}