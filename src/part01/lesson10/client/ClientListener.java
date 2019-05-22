package part01.lesson10.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.locks.Lock;

public class ClientListener implements Runnable {

    private BufferedReader reader;
//    private boolean socketWork = true;

    public ClientListener(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void setStop() {
//        socketWork = false;
//    }
}
