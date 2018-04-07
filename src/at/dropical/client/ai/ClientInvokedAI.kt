package at.dropical.client.ai

import at.dropical.shared.LocalRequestCache
import at.dropical.shared.net.requests.AddAiToGameRequest
import at.dropical.shared.net.requests.CreateGameRequest
import at.dropical.shared.net.requests.JoinRequest
import at.dropical.shared.net.requests.Request

/**
 * there is currently *no* method for getting the client into the server*/

class ClientInvokedAI: LocalClient(LocalRequestCache()) {
    val name="ClientInvokedAI"
    override fun run() {
        toServer(CreateGameRequest("TestGame"))
        toServer(JoinRequest("TestGame",name))
        toServer(AddAiToGameRequest("TestGame"))

    }

    override fun handleRequest(request: Request?) {

    }

}

fun main(vararg args:String){

}