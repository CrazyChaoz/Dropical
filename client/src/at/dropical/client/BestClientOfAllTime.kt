import at.dropical.client.Proxy
import at.dropical.shared.net.requests.CreateGameRequest
import at.dropical.shared.net.requests.JoinRequest
import at.dropical.shared.net.requests.ListRequest
import at.dropical.shared.net.requests.StartGameRequest
import java.net.Socket
import java.util.*

fun main(args: Array<String>) {
    val proxy = Proxy(Socket("localhost", 45000))
    val scanner = Scanner(System.`in`)

    var i = 1
    while (i != 0) {
        println("What do you want to do?")
        println("[1]: list currently open games")
        println("[2]: create new game")
        println("[3]: join a game")
        println("[4]: start a game")
        i = scanner.nextInt()
        when (i) {
            1 -> proxy.transmitToServer(ListRequest(true))
            2 -> {
                println("What should this game be called?")
                proxy.transmitToServer(CreateGameRequest(scanner.next()))
            }
            3 -> {
                println("Which game do you want to join?")
                proxy.transmitToServer(JoinRequest(scanner.next()))
            }
            4 ->{
                println("Which game do you want to start?")
                proxy.transmitToServer(StartGameRequest(scanner.next()))
            }
        }
    }
}