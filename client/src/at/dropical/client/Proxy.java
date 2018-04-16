package at.dropical.client;

import at.dropical.client.remote.RemoteTransmitter;
import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

import java.io.IOException;
import java.net.Socket;

public class Proxy extends Thread{
    private GameDataContainer gameDataContainer=null;
    private Transmitter transmitter;

    /**
     * local
     * W.I.P
     */
    public Proxy() {
//        this.transmitter = transmitter;
        this.start();
    }

    /**
     * remote
     * @param socket
     * @throws IOException
     */
    public Proxy(Socket socket) throws IOException {
        transmitter=new RemoteTransmitter(socket);
        this.start();
    }

    @Override
    public void run() {
        for(;;){
            try {
                Request request=transmitter.readRequest();
                if(request instanceof GameDataContainer)
                    gameDataContainer= (GameDataContainer) request;
            } catch (IOException|ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
