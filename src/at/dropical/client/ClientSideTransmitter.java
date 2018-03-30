package at.dropical.client;

import at.dropical.shared.net.requests.GameDataContainer;
import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

import java.io.*;
import java.net.Socket;

public class ClientSideTransmitter extends Transmitter {

    public ClientSideTransmitter(Socket socket) throws IOException {
        super(new ObjectInputStream(socket.getInputStream()), new ObjectOutputStream(socket.getOutputStream()));
    }

    @Override
    public void writeRequest(Request r) {
        try {
            ((ObjectOutputStream)super.getOutputStream()).writeObject(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Request readRequest() {
        try {
            return (Request) ((ObjectInputStream)super.getInputStream()).readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
