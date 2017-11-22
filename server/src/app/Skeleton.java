package app;

import network.StreamManager;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Represents a server-client Skeleton
 *
 * @author Gaetan
 */
public class Skeleton implements Runnable {

    private final Calculatrice calculatrice;
    private Socket clientSocket;
    private StreamManager streamManager;

    private static final String OPERATION_SEPARATOR = "-";
    private static final String OPERANDE_SEPARATOR = ":";

    Skeleton(Socket socket) {
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

        boolean stop = false;

        while (!stop) {
            // First method with string
//            stop = this.stringMethod();
            // Second method with object
            stop = this.objectMethod();
        }
        this.close();
    }

    private boolean objectMethod() {

        Object objectLoaded;

        try {
            objectLoaded = streamManager.receiveObject();

            if (objectLoaded == null || !(objectLoaded instanceof Message)) {
                System.err.println("Object not loading properly");
            }
        } catch (StreamCorruptedException exception) {
            return true;
        }

        Message m = (Message) objectLoaded;

        System.out.println(m);

        int a = m.getA();
        int b = m.getB();
        String methodName = m.getOperation();

        Method method = null;
        try {
            method = calculatrice.getClass().getDeclaredMethod(methodName, int.class, int.class);
        } catch (NoSuchMethodException e) {
            System.err.println("Could not find the given method on the previously given object");
            e.printStackTrace();
        }

        // Invoke the method with the given args
        try {
            assert method != null;
            int result = (int) method.invoke(calculatrice, a, b);
            streamManager.write(String.valueOf(result));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Read and parse a string from the socket
     *
     * @return
     */
    private boolean stringMethod() {
        String request = streamManager.readString();
        if (request.contains(OPERATION_SEPARATOR)) {
            String[] firstSplit = request.split(OPERATION_SEPARATOR);
            String operation = firstSplit[0];
            String[] secondSplit = firstSplit[1].split(OPERANDE_SEPARATOR);
            int a = Integer.parseInt(secondSplit[0]);
            int b = Integer.parseInt(secondSplit[1]);
            int result = -1;

            switch (operation) {
                case "multiply":
                    result = calculatrice.multiply(a, b);
                    break;
                case "add":
                    result = calculatrice.add(a, b);
                    break;
                case "substract":
                    result = calculatrice.substract(a, b);
                    break;
            }
            streamManager.write(String.valueOf(result));
        }
        return request.equals("exit");
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
