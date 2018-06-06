package at.dropical.wolliAI

import at.dropical.client.DropicalListener
import at.dropical.client.DropicalProxy
import at.dropical.shared.GameState
import at.dropical.shared.PlayerAction
import at.dropical.shared.net.container.CountDownContainer
import at.dropical.shared.net.container.GameDataContainer
import at.dropical.shared.net.container.GameOverContainer
import at.dropical.shared.net.container.ListDataContainer
import at.dropical.shared.net.requests.HandleInputRequest
import at.dropical.shared.net.requests.JoinRequest


// Created by julian on 11.01.18.
/**
 * This is the Bridge/Interface that one player uses
 * to communicate to any server.
 * It has convenience methods that allow easy access
 * to the DropicalProxy.
 *
 * The concrete Server implementation/communication is managed
 * in this class.
 */
class ServerAdapter(
    player: String = "Wolli AI",
    hostName: String = "localhost",
    port: Int = 45000
): DropicalListener {

    private var newestGameDataContainer: GameDataContainer? = null
    /** Which index in the Arrays in the container is this player. */
    private var index: Int = 0

    private val server: DropicalProxy = DropicalProxy(hostName, port, this)
    private val playerName = player
    init {
        /** Auto-queue to a game on the server. */
        server.writeToServer(JoinRequest(playerName))
    }


    /* The DropicalProxy calls these functions. */
    override fun updateUI(container: GameDataContainer?) {
        if (container != null) {
            val i = 0
            while (i < container.playernames.size) {
                if (container.playernames[i] == playerName) {
                    index = i
                    break
                }
            }
            newestGameDataContainer = container
        }
    }


    override fun countDown(container: CountDownContainer?) {
        println("Game starts in ${container?.seconds}")
    }
    override fun somebodyJoinedTheLobby(container: ListDataContainer?) {
        println("Players:")
        container?.names?.forEach{ println(it)}
    }
    override fun onGameOver(container: GameOverContainer?) {
        println("Game Over")
    }


    /* ----- Alte Schnittstelle ------------------- */

    fun getGameState(): GameState {
        val container = newestGameDataContainer
        if(container != null)
            return container.currentState
        else
            return GameState.LOADING
    }

    fun getArena(): Array<IntArray> {
        val container = newestGameDataContainer
        if(container != null) {
            println("Arena Größe: "+ container.arenas[index].size +"*"+ container.arenas[index][0].size)
            return container.arenas[index]
        } else
            return emptyArena
    }

    fun getTetromino(): Array<IntArray> {
        val container = newestGameDataContainer
        if(container != null)
            return container.currTrocks[index]
        else
            return emptyTetromino
    }
    fun getNextTetromino(): Array<IntArray> {
        val container = newestGameDataContainer
        if(container != null)
            return container.nextTrocks[index]
        else
            return emptyTetromino
    }

    fun getXPos(): Int {
        val container = newestGameDataContainer
        println("TrockX="+ container?.currTrockXs?.get(index))
        if(container != null)
            return container.currTrockXs[index]
        else
            return 0
    }
    fun getYPos(): Int {
        val container = newestGameDataContainer
        println("TrockY="+container?.currTrockXs?.get(index))
        if(container != null)
            return container.currTrockYs[index]
        else
            return 0
    }

    fun sendInput(input: PlayerAction) {
        //inputQueue.add(input)
        server.writeToServer(HandleInputRequest(playerName, input))
    }

    /** For performance and clarity */
    companion object {
        // Size: 20*10 TODO
        val emptyArena = Array<IntArray>(20, { IntArray(10) })
        //4*4
        val emptyTetromino = Array<IntArray>(4, { IntArray(4) })
    }
}