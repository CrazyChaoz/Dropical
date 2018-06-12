package at.dropical.server.transmitter;

import at.dropical.server.Server;
import at.dropical.shared.net.abstracts.SendableItem;
import at.dropical.shared.net.abstracts.Transmitter;

import java.io.*;
import java.util.logging.Level;

//Currently just a ObjectStreamTransmitter
//Future: JSON or completely bytewise

public class ObjectTransmitter implements Transmitter {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ObjectTransmitter(InputStream inputStream, OutputStream outputStream) throws IOException {
        super();
        //Die reihenfolge zählt ......
        this.outputStream=new ObjectOutputStream(outputStream);
        this.inputStream=new ObjectInputStream(new BufferedInputStream(inputStream));
    }

    @Override
    public SendableItem readRequest() throws IOException {
        try {
            return (SendableItem) inputStream.readObject();

        } catch(ClassNotFoundException e) {

            // Ignore the Request and wait for a valid one
            return readRequest();
        }
    }

    @Override
    public void writeRequest(SendableItem r) {
        try {
            outputStream.writeObject(r);
        } catch (IOException e) {

            Server.log(Level.SEVERE,"Couldn't send SendableItem " + r.getClass());
        }
    }

}
