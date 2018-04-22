package at.dropical.server.transmitter;

import at.dropical.server.ServerSideTransmitter;
import at.dropical.shared.net.requests.Request;

import java.io.*;

//Currently just a ObjectStreamTransmitter
//Future: JSON or completely bytewise

public class ObjectTransmitter extends ServerSideTransmitter {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ObjectTransmitter(InputStream inputStream, OutputStream outputStream) throws IOException {
        super();
        //Die reihenfolge zählt ......
        this.outputStream=new ObjectOutputStream(outputStream);
        this.inputStream=new ObjectInputStream(inputStream);
    }

    @Override
    public Request readRequest() throws IOException, ClassNotFoundException {
        return (Request) inputStream.readObject();
    }

    @Override
    public void writeRequest(Request r) {
        try {
            outputStream.writeObject(r);
        } catch (IOException e) {
//            Server.LOGGER.log(Level.SEVERE,"IOException on writing Request");
            System.err.println("Couldn't send Request " + r.toString());
        }
    }

}
