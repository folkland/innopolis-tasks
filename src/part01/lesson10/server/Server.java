package part01.lesson10.server;

import part01.lesson10.constants.Params;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {

    Database database;

    public void startServer() {
        database = new Database();
        try (ServerSocket server = new ServerSocket(Params.SERVER_PORT, 0, InetAddress.getByName(Params.ADDRESS))) {
            while (true) {
                Socket socket = server.accept();
//                System.out.println("get socket");
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String name = reader.readLine();
//                System.out.println(name);
                Connection connection = new Connection(socket, name, database);
                database.add(name, connection);
                connection.writeMessageToAll(name + " connect to chat");
//                System.out.println("connection create and send message to all");
                new Thread(connection).start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public Map<String, Connection> getAllClients() {
//        return connections;
//    }

    public void removeClient(String name) {
        database.remove(name);
    }
}
