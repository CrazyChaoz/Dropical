package at.dropical.server;


import at.dropical.server.transmitter.LocalServerTransmitter;

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
