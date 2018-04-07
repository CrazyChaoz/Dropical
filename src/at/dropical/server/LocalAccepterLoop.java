package at.dropical.server;


import at.dropical.shared.net.transmitter.LocalServerTransmitter;
import at.dropical.shared.net.transmitter.ObjectTransmitter;
import at.dropical.shared.net.transmitter.Transmitter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class LocalAccepterLoop extends Thread {
    private LocalServerTransmitter transmitter;


    public LocalAccepterLoop(LocalServerTransmitter transmitter) {
        this.transmitter = transmitter;
        this.start();
    }

    @Override
    public void run() {
        System.out.println("new Ai");
        for (; ; ) {
            new ServerSideRequestHandler(transmitter.readRequest(), transmitter);
        }
    }
}
