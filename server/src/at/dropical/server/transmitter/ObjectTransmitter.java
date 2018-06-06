package at.dropical.server.transmitter;

import at.dropical.server.Server;
import at.dropical.shared.net.abstracts.SendableItem;

import java.io.*;
import java.util.logging.Level;

//Currently just a ObjectStreamTransmitter
//Future: JSON or completely bytewise

public class ObjectTransmitter extends ServerSideTransmitter {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ObjectTransmitter(InputStream inputStream, OutputStream outputStream) throws IOException {
        super();
        //Die reihenfolge zählt ......
        this.outputStream=new ObjectOutputStream(outputStream);
        this.inputStream=new ObjectInputStream(new BufferedInputStream(inputStream));
    }

    @Override
    public SendableItem readRequest() throws IOException, ClassNotFoundException {
        return (SendableItem) inputStream.readObject();
    }

    @Override
    public void writeRequest(SendableItem r) {
        try {
            outputStream.writeObject(r);
        } catch (IOException e) {

            Server.LOGGER.log(Level.SEVERE,"Couldn't send SendableItem " + r.getClass());
        }
    }

}
