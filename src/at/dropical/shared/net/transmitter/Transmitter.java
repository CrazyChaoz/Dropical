package at.dropical.shared.net.transmitter;

import at.dropical.shared.net.requests.Request;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class Transmitter {
    private InputStream inputStream;
    private OutputStream outputStream;

    public Transmitter(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public abstract void writeRequest(Request r);
    public abstract Request readRequest();
}
