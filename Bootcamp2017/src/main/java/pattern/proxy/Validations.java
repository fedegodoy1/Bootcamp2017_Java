package pattern.proxy;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author federico
 */
public class Validations {

    public static boolean checkInet() {
        String address = "www.google.com";
        int timeOut = 5000;
        boolean connected = false;
        InetAddress inet;

        try {
            inet = InetAddress.getByName(address);
            connected = inet.isReachable(timeOut);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Validations.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Validations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connected;
    }
}
