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

        private static final String OPERATION_SEPARATOR = ":";
        private static final String OPERANDE_SEPARATOR = "-";


    private final StreamManager streamManager;
    private Socket clientSocket;

    public Stub(InetAddress serverInet, int serverPort) throws IOException {
        clientSocket = new Socket(serverInet, serverPort);
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
        String toSend = operation + OPERATION_SEPARATOR + a + OPERANDE_SEPARATOR + b;
        System.out.println("Sending: " + toSend);
        streamManager.write(toSend);
        String resultString = streamManager.readString();
        return Integer.parseInt(resultString);
    }
}
