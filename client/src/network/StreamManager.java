package network;

import java.io.*;
import java.net.Socket;

/**
 * Class used to manage all the network streams & exchanges
 *
 * @author Gaetan
 */
public class StreamManager {

    private final InputStream inputStream;
    private final OutputStream outputStream;

    StreamManager(Socket clientSocket) throws IOException {
        this.inputStream = clientSocket.getInputStream();
        this.outputStream = clientSocket.getOutputStream();
    }

    /**
     * Send the given file to the server
     *
     * @param fileName path to the file to send
     */
    public void sendFile(String fileName) {
        File file = new File(fileName);
        try {
            ByteStream.toStream(outputStream, file);
            System.out.println(file + " sent!");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * @param message Write a message to the outputstream
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
     * Send an object over tcp with ObjectOutputStream
     * @param object
     */
    public void sendObject(Object object) {
        ObjectOutputStream  oos = null;
        try {
            oos = new ObjectOutputStream(outputStream);
            oos.writeObject(object);
            System.out.println(object.getClass().getName() + " sent! ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close all the streams
     */
    public void closeAll() {
        try {
            this.inputStream.close();
            this.outputStream.close();
        } catch (IOException e) {
            System.err.println("Error closing the streams");
            e.printStackTrace();
        }
    }

}
