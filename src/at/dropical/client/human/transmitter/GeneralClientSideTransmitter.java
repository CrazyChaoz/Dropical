package at.dropical.client.human.transmitter;

import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

import java.io.*;
import java.net.Socket;

public class GeneralClientSideTransmitter extends Transmitter{

    public GeneralClientSideTransmitter(Socket socket) throws IOException {
        super(new ObjectInputStream(socket.getInputStream()), new ObjectOutputStream(socket.getOutputStream()));
    }

    @Override
    public void writeRequest(Request r) {
        try {
            ((ObjectOutputStream)outputStream).writeObject(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Request readRequest() {
        try {
            return (Request) ((ObjectInputStream)inputStream).readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
