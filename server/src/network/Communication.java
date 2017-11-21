package network;

import java.io.IOException;
import java.net.Socket;

/**
 * Represents a server-client Communication
 *
 * @author Gaetan
 */
public class Communication implements Runnable {

    private Socket clientSocket;
    private StreamManager streamManager;

    Communication(Socket socket) {
        this.clientSocket = socket;
        System.out.println(this);
        this.initialize();
    }

    private void initialize() {
        System.out.println("Communication running");
        try {
            this.streamManager = new StreamManager(clientSocket);
        } catch (IOException e) {
            System.err.println("Error getting the Input Stream");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
    }

    /**
     * Close and ends the communication
     */
    private void close() {
        String communicationInfo = this.toString();
        streamManager.finalize();
        System.out.println(communicationInfo + " finished");
    }

    @Override
    public String toString() {
        return "Communication on port " + clientSocket.getPort() + "(local port : " + clientSocket.getLocalPort() + ")" +
                "Adress : " + clientSocket.getLocalAddress();
    }
}
