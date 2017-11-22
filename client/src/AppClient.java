import app.Stub;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import static config.Config.SERVER_HOST;
import static config.Config.SERVER_PORT;

/**
 * Launcher
 *
 * @author Gaetan
 */
public class AppClient {

    public static void main(String[] args) {

        try {
            // Create the TCP Stub
            Stub stub = new Stub(Inet4Address.getByName(SERVER_HOST), SERVER_PORT);

            int r = stub.add(32, 13);
//            int r = stub.multiply(4, 3);
//            int r = stub.substract(4, 3);
            System.out.println("Result = " + r);
            stub.finish();

        } catch (UnknownHostException e) {
            System.err.println("Could not reach Server");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Could not connect to the Server");
            e.printStackTrace();
        }
    }
}
