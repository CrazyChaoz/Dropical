package at.dropical.remoteAI

import at.dropical.client.Proxy
import java.net.Socket

class Main{
    var proxy:Proxy = Proxy(Socket("localhost",45000))

}