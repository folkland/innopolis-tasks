package part01.lesson10.client;

import part01.lesson10.constants.Params;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Entity of Client logic
 *
 * @author folkland
 */
public class ClientImpl {

    private Socket socket;
    private String clientName;
    private BufferedWriter writer;
    private BufferedReader reader;

    /**
     * Constructor.
     * Create socket and buffers for read and write.
     * Start new Thread for listener. Address
     * @param name client name
     * @param port server port
     * @param addressName IP where search server
     */
    public ClientImpl(String name, int port, String addressName) {
        try {
            clientName = name;
            socket = new Socket(addressName, port);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ClientListener listener = new ClientListener(reader);
            new Thread(listener).start();
            sendMessage(clientName);
            Scanner scanner = new Scanner(System.in);
            String text = "";
            while (!Params.QUIT_PHRASE.equals(text)) {
                text = scanner.nextLine();
                System.out.println("nextLine");
                sendMessage(text);
            }
            close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send message to server
     * @param message
     */
    public void sendMessage(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close all connection and buffers
     */
    public void close() {
        try {
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
