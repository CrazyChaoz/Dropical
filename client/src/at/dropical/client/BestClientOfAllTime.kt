package at.dropical.client

import at.dropical.shared.net.requests.*
import java.net.Socket
import java.util.*

class BestClientOfAllTime(socket: Socket){
    var currentGameData: GameDataContainer?=null
    val proxy=Proxy()


    fun handleRequest(request: Request?) {
        when(request){
            is GameDataContainer ->{
                println("Handle this GameDataContainer")
                currentGameData=request
            }
            is ListDataContainer ->{
                println("Handle this ListRequest")
                if(request.gameNames==null)
                    return
                for (gameName in request.gameNames) {
                    println("Game: $gameName")
                }
            }
        }
    }



    fun run() {
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
            }
        }

    }
}

fun main(vararg args: String) {
    BestClientOfAllTime(Socket("localhost", 45000))
}