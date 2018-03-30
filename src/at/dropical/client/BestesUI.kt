package at.dropical.client

import at.dropical.shared.net.requests.ListRequest
import at.dropical.shared.net.requests.Request
import java.net.Socket

fun main(vararg args: String){
    val transmitter=ClientSideTransmitter(Socket("localhost",2345))

    transmitter.writeRequest(ListRequest(true))
    transmitter.readRequest()//gets a ListRequest back
}