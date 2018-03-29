package at.dropical.client

import at.dropical.shared.net.requests.ListRequest
import at.dropical.shared.net.requests.Request
import java.net.Socket

fun main(vararg args: String){
    val cst=ClientSideTransmitter(Socket("localhost",2345))

    cst.writeRequest(ListRequest(true))
    cst.readRequest()//gets a ListRequest back
}