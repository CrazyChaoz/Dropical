package at.dropical.shared.net.transmitter;

import at.dropical.shared.net.requests.Request;

import java.io.*;


//Currently just a ObjectStreamTransmitter
//Future: JSON or complete bytewise

public class HumanTransmitter implements Transmitter {
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    public HumanTransmitter(InputStream inputStream, OutputStream outputStream) {
        try {
            this.inputStream = new ObjectInputStream(inputStream);
            this.outputStream = new ObjectOutputStream(outputStream);
        } catch (IOException e) {e.printStackTrace();}
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
