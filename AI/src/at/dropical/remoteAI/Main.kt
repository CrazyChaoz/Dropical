package at.dropical.remoteAI

import at.dropical.client.Proxy
import at.dropical.shared.net.requests.CreateGameRequest
import at.dropical.shared.net.requests.ListRequest
import java.net.Socket

fun main(args: Array<String>) {
    var proxy = Proxy(Socket("localhost",45000))
    proxy.transmitToServer(ListRequest(true))
    proxy.transmitToServer(CreateGameRequest("asdf"))
    proxy.transmitToServer(ListRequest(true))
}
