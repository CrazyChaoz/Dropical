package fromWolli.logicState

import at.dropical.server.gamefield.TetrisArena
import at.dropical.server.gamefield.Tetromino
import at.dropical.shared.GameState
import at.dropical.shared.PlayerAction

// Created by julian on 24.11.17.
/**
 * This class is responsible for most of the game logic!
 *
 * <h1>Rules of Tetris</h1>
 * The **gamefield** ([TetrisArena][at.htlwels.it.wollersbergerjulian.tetris.server.gamefield.TetrisArena])
 * is a rectangular area where the [Tetrominos][at.htlwels.it.wollersbergerjulian.tetris.server.gamefield.Tetromino]
 * get placed with the following rules:<br></br><br></br>
 *
 * **Going** down: The first Tetromino appears at the
 * very top and falls downwards. When it arrives
 * at the bottom or falls onto another existing
 * Tetromino, it gets placed in that position, and
 * another Tetromino appears at the top.<br></br><br></br>
 *
 * The **player** controls the Tetromino that is falling
 * down. It can be moved to the left, right or
 * down, but not upwards. It can also be rotated
 * by 90° clockwise (but not counterclockwise
 * the teacher said).<br></br><br></br>
 *
 * A **Tetromino** consists of four blocks that are
 * connected. There are 7 different possible
 * arrangements ([TetrominoTypes][at.htlwels.it.wollersbergerjulian.tetris.server.gamefield.TetrominoTypes]).<br></br><br></br>
 *
 * The **Goal of the game** is to fit the
 * Tetrominos together without gaps. If an entire
 * horizontal line is full, it gets cleared
 * and lines above fall down.<br></br><br></br>
 *
 * If the **player makes mistakes** (covers
 * a gap) he/she needs to clear the line above
 * before he/she can fill the gap.<br></br><br></br>
 *
 * The **game ends** when the player makes
 * so many mistakes that the gamefield is almost
 * filled to the top so that a new Tetromino immediately
 * collides with the ones that are already there.<br></br><br></br>
 *
 * The game **makes fun** because the Tetrominos
 * fall down automatically slowly at the beginning
 * but the more lines you clear the faster it gets. TODO
 * You then have less time to react and you
 * realy can't think anymore what to and then
 * mistakes come and the game over is unavoidable.
 *
 * You get **points** for clearing lines and
 * the score gets saved.
 */
class RunningState(
        val stateManager: StateManager
) : GameLogicState {
    override val state: GameState=GameState.GAME_RUNNING

    override val tetrisArena: TetrisArena = TetrisArena()
    /** Current Tetromino  */
    override var tetromino: Tetromino = Tetromino.createRandom()
    /** The tetromino that will be used when the current fell down.  */
    override var nextTetromino: Tetromino  = Tetromino.createRandom()
    /** Distance from top. */
    override var h: Int = 0
    /** Distance from left. */
    override var w: Int = 0

    /** When placeTetromino says GameOver at some point.
     * To reset it to false, reset the game. */
    private var gameOver = false

    /** Initializes a fresh and empty game.  */
    init {
        /* Proper values are set here: */
        makeNextTetromino()
    }

    /** Called by the game loop.
     * Does the main logic every tick.
     *
     * @return number of lines cleared. */
    override fun run(goDown: Boolean, action: PlayerAction) :Int {
        /* First, User can do what he wants before the Tetromino
         * maybe gets placed */
        processUserInputs(action)

        /** After that maybe move down. */
        if (goDown)
            moveDownwardsOrPlace()

        // May have happened in processUserInputs or moveDownwards.
        if(gameOver)
            stateManager.changeGameState(GameState.GAME_LOST)

        /** At the end check if lines were cleared. */
        return tetrisArena.clearLines()
    }

    /** The nextTetromino gets placed at the top of the arena.
     * If the place is obscured, gameOver is set. */
    private fun makeNextTetromino() {
        tetromino = nextTetromino
        // TODO give the different players the same RNG
        nextTetromino = Tetromino.createRandom()
        //TODO Place it over the top properly
        h = 0
        // In the middle
        w = TetrisArena.width/2 -Tetromino.size/2

        if(! tetrisArena.checkTetromino(tetromino, h, w, true))
            gameOver = true
    }

    /** Auto-lowering the Tetromino a block.
     * If that is not possible, placeTetromino(). */
    private fun moveDownwardsOrPlace() {
        if (tetrisArena.checkTetromino(tetromino, h + 1, w, true)) {
            h++
        } else
            placeTetromino()
    }

    /** Places the current one and makes a new Tetromino.
     * If the placing fails, gameOver is set. */
    private fun placeTetromino() {
        if(tetrisArena.placeTetromino(tetromino, h, w))
            makeNextTetromino()
        else
            gameOver = true
    }

    /** Move the Tetromino left, right, down, turn.
     * Or change the game state to Paused, Stopped, ... */
    private fun processUserInputs(action: PlayerAction) {
        // Would be a switch-statement in Java.
        when (action) {
            PlayerAction.LEFT -> // If it is allowed, decrement w
                if (tetrisArena.checkTetromino(tetromino, h, w - 1, true))
                    w--

            PlayerAction.RIGHT -> // increment w
                if (tetrisArena.checkTetromino(tetromino, h, w + 1, true))
                    w++

            PlayerAction.DOWN -> // increment h
                if (tetrisArena.checkTetromino(tetromino, h + 1, w, true))
                    h++

            PlayerAction.DROP -> {// go down as long as possible
                while (tetrisArena.checkTetromino(tetromino, h + 1, w, true))
                    h++
                moveDownwardsOrPlace()
            }

            PlayerAction.UP -> {
                // Rotate and if rotated position is obscured, rotate back.
                tetromino.rotate()
                if (!tetrisArena.checkTetromino(tetromino, h, w, true))
                    tetromino.rotateBack()
            }

            PlayerAction.START -> {}
            PlayerAction.PAUSE -> stateManager.changeGameState(GameState.GAME_PAUSE)
            PlayerAction.QUIT -> stateManager.changeGameState(GameState.GAME_LOST)
            PlayerAction.NOKEY -> { }
            else -> throw IllegalArgumentException("Unknown PlayerAction")
        }
    }

    /** Forwards the call to the TetrisArena. */
    override fun addLines(lines: Int) {
        tetrisArena.addLines(lines)
    }
}
