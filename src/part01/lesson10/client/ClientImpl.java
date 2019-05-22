package part01.lesson10.client;

import com.sun.xml.internal.bind.v2.TODO;
import part01.lesson10.constants.Params;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ClientImpl {

    private Socket socket;
    private String clientName;
    private BufferedWriter writer;

    public ClientImpl(String name, int port, String addresName, int broadcastPort) {
        try {
            clientName = name;
            socket = new Socket(addresName, port);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            ClientListener listener = new ClientListener(new BufferedReader(new InputStreamReader(socket.getInputStream())));
//            listener.start();
//            ClientUDPListener udpListener = new ClientUDPListener(broadcastPort);
//            udpListener.start();
            new Thread(listener).start();
            sendMessage(clientName);
            //TODO при считывании происходит ерись
            Scanner scanner = new Scanner(System.in);
            String text = "";
            while (!Params.QUIT_PHRASE.equals(text)) {
                text = scanner.nextLine();
                sendMessage(text);
            }
//            listener.setStop();
//            udpListener.setStop();
            close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
