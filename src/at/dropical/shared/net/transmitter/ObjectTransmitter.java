package at.dropical.shared.net.transmitter;

import at.dropical.shared.net.requests.Request;

import java.io.*;


//Currently just a ObjectStreamTransmitter
//Future: JSON or complete bytewise

public class ObjectTransmitter extends Transmitter {
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    public ObjectTransmitter(InputStream inputStream, OutputStream outputStream) throws IOException {
        super(new ObjectInputStream(inputStream),new ObjectOutputStream(outputStream));
    }

    @Override
    public void writeRequest(Request r) {
        try {
            outputStream.writeObject(r);
        } catch (IOException e) {
            System.err.println("Couldn't send Request "+r.toString());
        }
    }

    @Override
    public Request readRequest() {
        try {
            return (Request) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found\n"+e.getMessage());
        }
        return null;
    }
}
