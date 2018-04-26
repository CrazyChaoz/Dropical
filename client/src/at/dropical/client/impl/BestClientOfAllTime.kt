import at.dropical.client.DropicalHandler
import at.dropical.client.DropicalProxy
import at.dropical.shared.GameState
import at.dropical.shared.PlayerAction
import at.dropical.shared.net.abstracts.Container
import at.dropical.shared.net.container.CountDownContainer
import at.dropical.shared.net.container.ListDataContainer
import at.dropical.shared.net.requests.*
import java.io.IOException
import java.util.*

object BestClientOfAllTime:DropicalHandler{
    private val scanner = Scanner(System.`in`)

    @Throws(IOException::class)
    fun BestClientOfAllTime(){
        val proxy = DropicalProxy("localhost", 45000, this)
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
            println("[8]: create a game with a specific number of players")
            i = scanner.nextInt()
            when (i) {
                1 -> proxy.writeToServer(ListGamesRequest())
                2 -> {
                    println("What should this game be called?")
                    proxy.writeToServer(CreateGameRequest(scanner.next()))
                }
                3 -> {
                    println("Which game do you want to join?")
                    proxy.writeToServer(JoinRequest(scanner.next(), playername))
                }
                4 -> proxy.writeToServer(ListPlayersRequest())
                5 -> {
                    println("Which game do you want to start?")
                    proxy.writeToServer(StartGameRequest(scanner.next()))
                }
                6 -> {
                    println("What Input Key do you want to send?")
                    proxy.writeToServer(HandleInputRequest(playername, getInput()))
                }
                7 -> proxy.writeToServer(JoinRequest(playername))
                8 -> {
                    println("What should this game be called?")
                    val gameName = scanner.next()
                    println("How many players should be able to play this game?")
                    val playerCount = scanner.nextInt()
                    proxy.writeToServer(CreateGameRequest(gameName, playerCount))
                }
            }
        }
    }

    private fun getInput(): PlayerAction {
        while (true) {
            when (scanner.next()) {
                "w" -> return PlayerAction.UP
                "a" -> return PlayerAction.LEFT
                "s" -> return PlayerAction.DOWN
                "d" -> return PlayerAction.RIGHT
                " " -> return PlayerAction.DROP
            }
        }
    }

    override fun handle(container: Container) {
        if (container.currentState != null) {
            when (container.currentState) {
                GameState.LOBBY -> {
                    println("#########")
                    for (s in (container as ListDataContainer).gameNames)
                        println("Spieler: $s")
                    println("#########")
                }
                GameState.GAME_LIST -> {
                    println("#########")
                    for (s in (container as ListDataContainer).gameNames)
                        println("Game: $s")
                    println("#########")
                }
                GameState.PAUSE, GameState.RUNNING -> println("GameDataContainer recieved")
                GameState.STARTING -> println("Time until Start: " + (container as CountDownContainer).seconds)
                GameState.GAME_OVER, GameState.GAME_WON, GameState.GAME_LOST -> println("Game is Over")
            }
        }
    }
}
