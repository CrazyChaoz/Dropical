package at.htlwels.it.wollersbergerjulian.tetris.server.main

import at.dropical.server.logicState.GameLogicState
import at.dropical.server.logicState.PausedState
import at.dropical.server.logicState.RunningState
import at.dropical.server.logicState.WonLostState
import at.dropical.shared.GameState


// Created by julian on 24.11.17.

/**
 * This loop runs several times a second with the
 * same time interval.
 *
 * @see at.htlwels.it.wollersbergerjulian.tetris.server.logicState.RunningState
 */
class SinglePlayer(
        val multiPlayerManager: MultiPlayerManager
) {
    /** Only the state that is currently stored in
     * gameLogicState gets updated. */
    private val runningGameLogic = RunningState(multiPlayerManager)
    private val pausedGameLogic = PausedState(multiPlayerManager)
    private val wonLostGameLogic = WonLostState(multiPlayerManager, runningGameLogic)

    private var gameLogicState : GameLogicState = runningGameLogic
    private var currentGameState = GameState.GAME_RUNNING

    /** The number of steps that have happened. */
    private var tick: Int = 0


//    internal fun run(request: PollRequest): PollRequest {
//        // FIXME The timing depends on how often the client calls this function.
//        tick++
//
//        // Every 30 ticks the stone goes down.
//        val linesCleared = gameLogicState.run(tick%50 == 0, request.playerAction)
//
//        if(linesCleared >0)
//            multiPlayerManager.addLinesToAll(this, linesCleared)
//
//        // gameLogicState may have another value now!
//        request.playerAction = PlayerAction.NOKEY
//        request.gameState = currentGameState
//        request.arena = gameLogicState.tetrisArena.toArray()
//        request.actTetronimo = gameLogicState.tetromino.toArray()
//        request.actTetronimoX = gameLogicState.h
//        request.actTetronimoY = gameLogicState.w
//        request.nextTetronimo = gameLogicState.nextTetromino.toArray()
//
//        return request
//    }

    /** To change a state this function is not called in gameLogicState.run()
     * directly. It calls the MultiPlayerManager which calls
     * this function, but for all players. */
    internal fun changeGameState(state: GameState) {
        currentGameState = state
        gameLogicState = when(state) {
            GameState.GAME_RUNNING -> runningGameLogic
            GameState.GAME_PAUSE -> pausedGameLogic
            GameState.GAME_LOST, GameState.GAME_WON, GameState.GAME_OVER  -> wonLostGameLogic
        }
    }

    /** Forwards the call to the TetrisArena. */
    fun addLines(lines: Int) {
        gameLogicState.addLines(lines)
    }
}
