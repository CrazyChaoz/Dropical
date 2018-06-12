package at.dropical.server.transmitter;

import at.dropical.server.Server;
import at.dropical.shared.net.abstracts.SendableItem;
import at.dropical.shared.net.abstracts.Transmitter;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.logging.Level;

//Currently just a ObjectStreamTransmitter
//Future: JSON or completely bytewise

public class ObjectTransmitter implements Transmitter, AutoCloseable {

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    /** May contain a socket that needs to be closed at the end. */
    private AutoCloseable closeable;

    public ObjectTransmitter(InputStream inputStream, OutputStream outputStream, @Nullable AutoCloseable closeable) throws IOException {
        super();
        //Die reihenfolge zählt ......
        this.outputStream=new ObjectOutputStream(outputStream);
        this.inputStream=new ObjectInputStream(new BufferedInputStream(inputStream));
        this.closeable = closeable;
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

    @Override
    public void close() {
        try {
            inputStream.close();
            outputStream.close();
            closeable.close();
        } catch(Exception ignored) { }
    }
}
