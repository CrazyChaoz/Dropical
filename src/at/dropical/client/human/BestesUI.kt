package at.dropical.client.human

import at.dropical.shared.net.requests.ListRequest
import java.net.Socket

class BestesUI(socket: Socket) : HumanClient(socket){
    override fun run() {
        toServer(ListRequest(true))

    }
}

fun main(vararg args: String) {
    BestesUI(Socket("localhost", 2345))
}