package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Represents a TCP Client able to ask a remote call to a server
 * @author Gaetan
 */
public class Client {

    private final StreamManager streamManager;
    private Socket clientSocket;

    public Client(InetAddress serverInet, int serverPort) throws IOException {
        clientSocket = new Socket(serverInet, serverPort);
        this.streamManager = new StreamManager(clientSocket);
        System.out.println("Client running");
        this.streamManager.closeAll();
    }

}
