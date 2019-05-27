package part01.lesson10.server;

import part01.lesson10.constants.Params;

import java.io.*;
import java.net.*;

/**
 * Server entity. Store all clients
 *
 * @author folkalnd
 */
public class Server {

    Database database;

    /**
     * Start server socket. Register client and store clients in database.
     */
    public void startServer() {
        database = new Database();
        try (ServerSocket server = new ServerSocket(Params.SERVER_PORT, 0, InetAddress.getByName(Params.ADDRESS))) {
            while (true) {
                Socket socket = server.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String name = reader.readLine();
                Connection connection = new Connection(socket, name, database, reader);
                database.add(name, connection);
                connection.writeMessageToAll(name + " connect to chat");
                new Thread(connection).start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
