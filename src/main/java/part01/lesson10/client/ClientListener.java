package part01.lesson10.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

/**
 * Class for listening answer from server
 *
 * @author folkland
 */
public class ClientListener implements Runnable {

    private BufferedReader reader;

    public ClientListener(BufferedReader reader) {
        this.reader = reader;
    }

    /**
     * Listening answer from message in new Thread
     */
    @Override
    public void run() {
        try {
            String message;
            while (true) {
                message = reader.readLine();
                System.out.println(message);
                LockSupport.parkNanos(1000);
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }
}
