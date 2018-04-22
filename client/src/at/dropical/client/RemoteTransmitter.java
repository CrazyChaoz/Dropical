package at.dropical.client;

import at.dropical.shared.net.requests.Request;
import at.dropical.shared.net.transmitter.Transmitter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RemoteTransmitter implements Transmitter {

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public RemoteTransmitter(Socket socket){
        try {
            inputStream=new ObjectInputStream(socket.getInputStream());
            outputStream=new ObjectOutputStream(socket.getOutputStream());
        }catch (IOException e){}
    }

    @Override
    public void writeRequest(Request r) {
        try {
            outputStream.writeObject(r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Request readRequest() throws IOException {
        try {
            return (Request) inputStream.readObject();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
