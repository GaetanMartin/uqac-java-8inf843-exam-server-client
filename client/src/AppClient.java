import network.Client;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import static config.Config.SERVER_HOST;
import static config.Config.SERVER_PORT;

/**
 * Launcher
 * @author Gaetan
 */
public class AppClient {

    public static void main (String[] args) {

        try {
            // Create the TCP Client
            Client client = new Client(Inet4Address.getByName(SERVER_HOST), SERVER_PORT);

            // TODO: Call client here


        } catch (UnknownHostException e) {
            System.err.println("Could not reach Server");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Could not connect to the Server");
            e.printStackTrace();
        }
    }
}
