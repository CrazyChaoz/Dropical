package at.dropical.wolliAI

import at.dropical.shared.GameState
import at.dropical.shared.PlayerAction
import java.util.*


// Created by julian on 11.01.18.
/**
 * This is the Bridge/Interface that one player field uses
 * to communicate to any server.
 * It has convenience methods that allow easy access
 * to the PollRequest-Object.
 *
 * The concrete Server implementation/communication is managed
 * in this class. (?)
 */
class ServerAdapter (
    private val server: TetrisServer,
    private val playerNo: Int
){
    private var latestPollRequest = PollRequest("I have no idea why this text is here")

    /** Uses a queue to make it possible for the user to press
     * several buttons in one frame. Every frame/update one
     * action is sent. */
    private val inputQueue = LinkedList<PlayerAction>()

    public var debugMode = false;

    /** Should be called at the end of each renderCycle.
     * This then transfers the PollRequest-Object. */
    fun transmitData() {
        // First edit the request
        // If there is no element in the list, then NOKEY.
        latestPollRequest.playerAction =
                inputQueue.poll() ?: PlayerAction.NOKEY
        latestPollRequest.playerNo = playerNo

        // send Request and recieve new one.
        latestPollRequest = server.pollGameState(latestPollRequest)
    }

    fun getGameState(): GameState {
        return latestPollRequest.gameState
    }

    fun getArena(): Array<IntArray> {
        return latestPollRequest.arena
    }

    fun getTetromino(): Array<IntArray> {
        return latestPollRequest.actTetronimo
    }
    fun getNextTetromino(): Array<IntArray> {
        return latestPollRequest.nextTetronimo
    }

    fun getXPos(): Int {
        return latestPollRequest.actTetronimoX
    }
    fun getYPos(): Int {
        return latestPollRequest.actTetronimoY
    }

    /** Uses a queue to make it possible for the user to press
     * several buttons in one frame. Every frame/update one
     * action is sent. */
    fun sendInput(input: PlayerAction) {
        inputQueue.add(input)
    }
}