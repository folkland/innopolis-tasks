package part01.lesson10.server;

import java.util.*;

/**
 * Store information about clients. Manipulate with this information
 */
public class Database {

    Map<String, Connection> connections;
    List<String> clientNames;

    public Database() {
        connections = new HashMap<>();
        clientNames = new ArrayList<>();
    }

    /**
     * Add new client to database
     * @param name client name
     * @param connection connection to client
     */
    public void add(String name, Connection connection) {
        connections.put(name, connection);
        clientNames.add(name);
    }

    /**
     * Remove client from database when client leave from chat
     * @param name client name
     */
    public void remove(String name) {
        connections.remove(name);
        clientNames.remove(name);
    }

    /**
     * Returns allInformation from database
     * @return connections with name
     */
    public Map<String, Connection> getConnections() {
        return connections;
    }

    /**
     * Returns all client names
     * @return list of name
     */
    public List<String> getClientNames() {
        return clientNames;
    }
}
