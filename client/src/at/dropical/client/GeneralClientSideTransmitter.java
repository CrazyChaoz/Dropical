package at.dropical.client;

import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.ServerSideTransmitter;

import java.io.*;
import java.net.Socket;

public class GeneralClientSideTransmitter extends ServerSideTransmitter {

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
    public Request readRequest() throws IOException {
        try {
            return (Request) ((ObjectInputStream) inputStream).readObject();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
