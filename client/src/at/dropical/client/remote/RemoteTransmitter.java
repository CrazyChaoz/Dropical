package at.dropical.client.remote;

import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RemoteTransmitter implements Transmitter {
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public RemoteTransmitter(Socket server) throws IOException {
        inputStream = new ObjectInputStream(server.getInputStream());
        outputStream = new ObjectOutputStream(server.getOutputStream());
    }

    @Override
    public void writeRequest(Request r) throws IOException {
        outputStream.writeObject(r);
    }

    @Override
    public Request readRequest() throws IOException, ClassNotFoundException {
        return (Request) inputStream.readObject();
    }
}
