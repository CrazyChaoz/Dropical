package at.dropical.client

import at.dropical.shared.net.requests.*
import java.net.Socket
import java.util.*

class BestClientOfAllTime(socket: Socket):Thread(){
    val proxy=Proxy(socket)

    override fun run() {
        //Things that Clients do
        var i=1
        while (i!=0){
            println("What do you want to do ?")
            i=Scanner(System.`in`).nextInt()
            when (i) {
                1 -> proxy.transmitToServer(ListRequest(true))
                2 -> proxy.transmitToServer(CreateGameRequest("Super Secret Game"))
                3 -> proxy.transmitToServer(AddAiToGameRequest("Super Secret Game"))
                4 -> {
                    print("Write the GameName here: ")
                    proxy.transmitToServer(CreateGameRequest(Scanner(System.`in`).next()))}
                5 -> proxy.transmitToServer(JoinRequest("Super Secret Game"))
            }
        }

    }
}

fun main(vararg args: String) {
    BestClientOfAllTime(Socket("localhost", 45000)).start()
}