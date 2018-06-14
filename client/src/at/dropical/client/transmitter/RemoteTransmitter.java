package at.dropical.client.transmitter;

import at.dropical.shared.net.abstracts.SendableItem;
import at.dropical.shared.net.abstracts.Transmitter;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RemoteTransmitter implements Transmitter {

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public RemoteTransmitter(Socket socket) throws IOException {
        inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void writeRequest(SendableItem r) {
        try {
            outputStream.writeObject(r);
        } catch(IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Override
    public SendableItem readRequest() throws IOException {
        try {
            return (SendableItem) inputStream.readObject();

        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
