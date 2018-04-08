package at.dropical.shared.net.transmitter;

import at.dropical.server.Server;
import at.dropical.shared.net.requests.Request;

import java.io.*;
import java.util.logging.Level;


//Currently just a ObjectStreamTransmitter
//Future: JSON or completely bytewise

public class ObjectTransmitter extends Transmitter {

    public ObjectTransmitter(InputStream inputStream, OutputStream outputStream) throws IOException {
        super(inputStream, outputStream);

        //Die reihenfolge zählt ......
        this.outputStream=new ObjectOutputStream(outputStream);
        this.inputStream=new ObjectInputStream(inputStream);
    }

    @Override
    public void writeRequest(Request r) {
        try {
            ((ObjectOutputStream) outputStream).writeObject(r);
        } catch (IOException e) {
            Server.LOGGER.log(Level.SEVERE,"IOException on writing Request");
            System.err.println("Couldn't send Request " + r.toString());
        }
    }

    @Override
    public Request readRequest() {
        try {
            return (Request) ((ObjectInputStream) inputStream).readObject();
        } catch (IOException e) {
            Server.LOGGER.log(Level.SEVERE,"IOException on reading Request");
        } catch (ClassNotFoundException e) {
            Server.LOGGER.log(Level.SEVERE,"Incompatible Class recieved");
            System.err.println("Class not found\n" + e.getMessage());
        }
        return null;
    }
}
