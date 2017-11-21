package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Represents a TCP Server
 * @author Gaetan
 */
public class Server {

    private ServerSocket serverSocket;

    public Server(int serverPort) {
        System.out.println("Launching Server");

        try {
            serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            System.err.println("Error launching Server");
            e.printStackTrace();
        }

        System.out.println("Server running");


        while (true) {
            try {
                Socket connectionSocket = serverSocket.accept();
                Communication clientCommunication = new Communication(connectionSocket);
                new Thread(clientCommunication).run();
            } catch (IOException e) {
                System.err.println("Error accepting new connection");
                e.printStackTrace();
            }
        }
    }
}
