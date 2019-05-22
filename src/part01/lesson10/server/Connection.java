package part01.lesson10.server;

import part01.lesson10.constants.Params;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;

public class Connection implements Runnable {

    Socket socket;
    BufferedReader reader;
    String name;
    Database database;

    public Connection(Socket socket, String name, Database database) {
        this.database = database;
        this.name = name;
        this.socket = socket;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("here");
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                if (!Params.QUIT_PHRASE.equals(message)) {
                    database.remove(name);
                    writeMessageToAll(name + " has left from chat");
                    break;
                }
                if (message.charAt(0) == '@') {
                    String nickname = message.substring(1, message.indexOf(' '));
                    writePrivateMessage(nickname, name + ": " + message);
                    continue;
                }
                writeMessageToAll(name + ": " + message);
            }
            socket.close();
        } catch (IOException e) {
            database.remove(name);
            writeMessageToAll(name + " has left from chat");
            System.out.println(name + " has left from chat");
//            e.printStackTrace();
        }
    }

    private void writePrivateMessage(String nickname, String message) {
        writeMessage(message);
        Connection connection = database.getConnections().get(nickname);
        connection.writeMessage(message);
    }

    private void writeMessage(String message) {
        try {
            System.out.println("write");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMessageToAll(String message) {
//        try {
            List<String> clients = database.getClientNames();
            Map<String, Connection> connectionMap = database.getConnections();
            for (String name: clients) {
                Connection connection = connectionMap.get(name);
                connection.writeMessage(message);
            }
//            byte[] text = message.getBytes();
//            DatagramPacket dp = new DatagramPacket(text, text.length, InetAddress.getByName(Params.ADDRESS), 6226);
//            DatagramPacket dp = new DatagramPacket(text, text.length);
//            DatagramSocket ds = new DatagramSocket();
//            ds.send(dp);
//            ds.close();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
