import app.Server;

public class AppServer {

    /**
     * Port to run on
     */
    private final static int SERVER_PORT = 6789;

    /**
     * Launch the server on the given port
     * @param args
     */
    public static void main(String[] args) {
        new Server(SERVER_PORT);
    }
}
