package part01.lesson10.server;

import part01.lesson10.constants.Params;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

/**
 * Connection between server and client by TCP
 *
 * @author folkland
 */
public class Connection implements Runnable {

    Socket socket;
    BufferedReader reader;
    String name;
    Database database;
    BufferedWriter writer;

    public Connection(Socket socket, String name, Database database, BufferedReader reader) {
        this.database = database;
        this.name = name;
        this.socket = socket;
        this.reader = reader;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start thread with listener for client and send message to all client in chat
     */
    @Override
    public void run() {
        try {
            String message;
            while (true) {
                message = reader.readLine();
                if (Params.QUIT_PHRASE.equals(message)) {
                    database.remove(name);
                    writeMessageToAll(name + " has left from chat");
                    break;
                }
                if (message.charAt(0) == '@') {
                    String nickname = message.substring(1, message.indexOf(' '));
                    writePrivateMessage(nickname, name + ": " + message);
                    continue;
                }
                System.out.println("connection");
                writeMessageToAll(name + ": " + message);
            }
            socket.close();
        } catch (IOException e) {
            database.remove(name);
            writeMessageToAll(name + " has left from chat");
            System.out.println(name + " has left from chat");
        }
    }

    /**
     * Write private message between few clients
     *
     * @param nickname client to whom send message
     * @param message
     */
    private void writePrivateMessage(String nickname, String message) {
        writeMessage(message);
        Connection connection = database.getConnections().get(nickname);
        connection.writeMessage(message);
    }

    /**
     * write message from server to client
     *
     * @param message text of message
     */
    private void writeMessage(String message) {
        try {
            System.out.println("write");
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send message to all clients in chat
     * @param message
     */
    public void writeMessageToAll(String message) {
        List<String> clients = database.getClientNames();
        Map<String, Connection> connectionMap = database.getConnections();
        for (String name : clients) {
            Connection connection = connectionMap.get(name);
            connection.writeMessage(message);
        }
    }
}
