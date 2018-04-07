package at.dropical.client.human

import at.dropical.shared.net.requests.CreateGameRequest
import at.dropical.shared.net.requests.GameDataContainer
import at.dropical.shared.net.requests.ListRequest
import at.dropical.shared.net.requests.Request
import java.net.Socket

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
                for (gameName in request.gameNames) {
                    println(gameName)
                }
            }
        }
    }



    override fun run() {
        //Things that Clients do
        toServer(ListRequest(true))
        toServer(CreateGameRequest("Super Secret Game"))
    }
}

fun main(vararg args: String) {
    BestClientOfAllTime(Socket("localhost", 2345))
}