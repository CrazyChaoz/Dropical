import at.dropical.client.Proxy
import at.dropical.shared.PlayerAction
import at.dropical.shared.net.requests.*
import java.io.IOException
import java.net.Socket
import java.util.*

object BestClientOfAllTime {
    private val scanner = Scanner(System.`in`)
    private val input: PlayerAction
        get() {
            val retval: PlayerAction? = null
            while (retval == null) {
                when (scanner.next()) {
                    "w" -> return PlayerAction.UP
                    "a" -> return PlayerAction.LEFT
                    "s" -> return PlayerAction.DOWN
                    "d" -> return PlayerAction.RIGHT
                    " " -> return PlayerAction.DROP
                }
            }
            return retval
        }

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val proxy = Proxy(Socket("localhost", 45000))
        val playername: String

        println("Whats your name?")
        playername = scanner.next()


        var i = 1
        while (i != 0) {
            println("So, $playername, what do you want to do?")
            println("[1]: list currently open games")
            println("[2]: create new game")
            println("[3]: join a game")
            println("[4]: list connected players to your game")
            println("[5]: start a game")
            println("[6]: send input to the server")
            println("[7]: autoQueue")
            i = scanner.nextInt()
            when (i) {
                1 -> proxy.transmitToServer(ListGamesRequest())
                2 -> {
                    println("What should this game be called?")
                    proxy.transmitToServer(CreateGameRequest(scanner.next()))
                }
                3 -> {
                    println("Which game do you want to join?")
                    proxy.transmitToServer(JoinRequest(scanner.next(), playername))
                }
                4 -> proxy.transmitToServer(ListPlayersRequest())
                5 -> {
                    println("Which game do you want to start?")
                    proxy.transmitToServer(StartGameRequest(scanner.next()))
                }
                6 -> {
                    println("What Input Key do you want to send?")
                    proxy.transmitToServer(HandleInputRequest(playername, input))
                }
                7 -> proxy.transmitToServer(JoinRequest(playername))
            }
        }
    }
}
