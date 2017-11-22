package app;

import network.StreamManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Represents a TCP Stub able to ask a remote call to a server
 *
 * @author Gaetan
 */
public class Stub implements CalculatriceItf {

    private final StreamManager streamManager;

    public Stub(InetAddress serverInet, int serverPort) throws IOException {
        Socket clientSocket = new Socket(serverInet, serverPort);
        this.streamManager = new StreamManager(clientSocket);
        System.out.println("Stub running");
    }

    public void finish() {
        this.streamManager.write("exit");
        this.streamManager.closeAll();
    }

    @Override
    public int multiply(int a, int b) {
        return operation("multiply", a, b);
    }

    @Override
    public int add(int a, int b) {
        return operation("add", a, b);
    }

    @Override
    public int substract(int a, int b) {
        return operation("substract", a, b);
    }

    private int operation(String operation, int a, int b) {
        Message toSend = new Message(operation, a, b);
        System.out.println("Sending: " + toSend.toString());

        // First method: we send the string
//        streamManager.write(toSend.toString());
        // Second method: we send a message object
        streamManager.sendObject(toSend);
        String resultString = streamManager.readString();

        if (!resultString.contains("err")) {
            return Integer.parseInt(resultString);
        }
        return -1;

    }
}
