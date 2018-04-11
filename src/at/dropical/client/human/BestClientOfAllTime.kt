package at.dropical.client.human

import at.dropical.shared.net.requests.*
import java.net.Socket
import java.util.*

class BestClientOfAllTime(socket: Socket) : RemoteClient(socket){
    var currentGameData:GameDataContainer?=null


    override fun handleRequest(request: Request?) {
        when(request){
            is GameDataContainer ->{
                println("Handle this GameDataContainer")
                currentGameData=request
            }
            is ListRequest ->{
                println("Handle this ListRequest")
                if(request.gameNames==null)
                    return
                for (gameName in request.gameNames) {
                    println("Game: $gameName")
                }
            }
        }
    }



    override fun run() {
        //Things that Clients do
        var i=1
        while (i!=0){
            println("What do you want to do ?")
            i=Scanner(System.`in`).nextInt()
            when (i) {
                1 -> toServer(ListRequest(true))
                2 -> toServer(CreateGameRequest("Super Secret Game"))
                3 -> toServer(AddAiToGameRequest("Super Secret Game"))
                4 -> {
                    print("Write the GameName here: ")
                    toServer(CreateGameRequest(Scanner(System.`in`).next()))}
            }
        }

    }
}

fun main(vararg args: String) {
    BestClientOfAllTime(Socket("localhost", 45000))
}