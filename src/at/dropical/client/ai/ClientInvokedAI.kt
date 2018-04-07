package at.dropical.client.ai

import at.dropical.server.Server
import at.dropical.shared.LocalRequestCache
import at.dropical.shared.net.requests.AddAiToGameRequest
import at.dropical.shared.net.requests.CreateGameRequest
import at.dropical.shared.net.requests.JoinRequest
import at.dropical.shared.net.requests.Request


class ClientInvokedAI: LocalClient(LocalRequestCache()) {
    val name="ClientInvokedAI"
    override fun run() {
        toServer(CreateGameRequest("TestGame"))
//        Thread.sleep(500)
        toServer(JoinRequest("TestGame",name))
        toServer(AddAiToGameRequest("TestGame"))

    }

    override fun handleRequest(request: Request?) {

    }

}

fun main(vararg args:String){
    ClientInvokedAI()
}