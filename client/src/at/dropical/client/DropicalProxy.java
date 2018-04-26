package at.dropical.client;

import at.dropical.client.transmitter.RemoteTransmitter;
import at.dropical.shared.net.abstracts.Container;
import at.dropical.shared.net.abstracts.Request;
import at.dropical.shared.net.abstracts.Transmitter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.Socket;

public final class DropicalProxy implements Runnable {

    @NotNull
    private Transmitter transmitter;
    @NotNull
    private DropicalHandler handlerImpl;

    public DropicalProxy(String host, int port, DropicalHandler handlerImpl) throws IOException {
//        if(host.equals("localhost")||host.equals("127.0.0.1"))
//            transmitter=new LocalTransmitter(new LocalRequestCache());
//        else
        transmitter = new RemoteTransmitter(new Socket(host, port));
        this.handlerImpl = handlerImpl;
        new Thread(this).start();
    }

    public void writeToServer(Request request) {
        transmitter.writeRequest(request);
    }

    @Override
    public void run() {
        for (; ; ) {
            try {
                handlerImpl.handle((Container) transmitter.readRequest());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
