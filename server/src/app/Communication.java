package app;

import network.StreamManager;

import java.io.IOException;
import java.net.Socket;

/**
 * Represents a server-client Communication
 *
 * @author Gaetan
 */
public class Communication implements Runnable {

    private final Calculatrice calculatrice;
    private Socket clientSocket;
    private StreamManager streamManager;

    private static final String OPERATION_SEPARATOR = ":";
    private static final String OPERANDE_SEPARATOR = "-";

    Communication(Socket socket) {
        this.clientSocket = socket;
        System.out.println(this);
        calculatrice = new Calculatrice();
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
        String request = "";
        while (!request.equals("exit")) {
            request = streamManager.readString();

            if (request.contains(OPERATION_SEPARATOR)) {
                String[] firstSplit = request.split(OPERATION_SEPARATOR);
                String operation = firstSplit[0];
                String[] secondSplit = firstSplit[1].split(OPERANDE_SEPARATOR);
                int a = Integer.parseInt(secondSplit[0]);
                int b = Integer.parseInt(secondSplit[1]);
                int result = -1;

                if (operation.equals("multiply")) {
                    result = calculatrice.multiply(a, b);
                } else if (operation.equals("add")) {
                    result = calculatrice.add(a, b);
                } else if (operation.equals("substract")) {
                    result = calculatrice.substract(a, b);
                }
                streamManager.write(String.valueOf(result));
            }
        }
        this.close();
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
