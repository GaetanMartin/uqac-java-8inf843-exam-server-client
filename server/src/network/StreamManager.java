package network;

import java.io.*;
import java.net.Socket;

/**
 * Class used to manage all the network streams & exchanges
 * @author Gaetan
 */
public class StreamManager {


    private final static int BUFFER_DEFAULT_SIZE = 1024;

    private final InputStream inputStream;
    private final OutputStream outputStream;

    StreamManager(Socket clientSocket) throws IOException {
        this.inputStream = clientSocket.getInputStream();
        this.outputStream = clientSocket.getOutputStream();
    }

    /**
     * Receive a file with a dataInputStream from the input stream
     */
    public File receiveFile(File fileToCreate) throws IOException {

        ByteStream.toFile(inputStream, fileToCreate);
        return fileToCreate;
    }

    /**
     * Read a string from the inputstream
     *
     * @return String the message read
     */
    public String readString() {
        String message = "error";
        try {
            message = ByteStream.toString(inputStream);
            System.out.println("Received: " + message);
            return message;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * Write a message
     *
     * @param message Write a message in the outputstream
     */
    public void write(String message) {
        try {
            ByteStream.toStream(outputStream, message);
            System.out.println("Sent: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read an object from the socket inputstream
     * @return
     */
    public Object receiveObject() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(inputStream);
            Object object = ois.readObject();
            System.out.println("Object " + object.getClass().getName() + " succesfully received! ");
            return object;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Close all the streams
     */
    public void finalize() {
        try {
            this.inputStream.close();
            this.outputStream.close();
        } catch (IOException e) {
            System.err.println("Error closing the streams");
            e.printStackTrace();
        }
    }
}
