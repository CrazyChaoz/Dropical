package at.dropical.client

import java.net.Socket

fun main(vararg args: String){
    ClientSideTransmitter(Socket("localhost",2345))
}