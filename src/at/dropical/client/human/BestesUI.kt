package at.dropical.client.human

import at.dropical.Client
import at.dropical.shared.net.requests.GameDataContainer
import at.dropical.shared.net.requests.ListRequest
import java.net.Socket

class BestesUI : Client {
    override fun run() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCurrentGameDataContainer(request: GameDataContainer?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLastListRequest(request: ListRequest?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

fun main(vararg args: String) {
    val transmitter = ClientSideTransmitter(Socket("localhost", 2345), BestesUI())

    transmitter.writeRequest(ListRequest(true))

}