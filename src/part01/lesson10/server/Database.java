package part01.lesson10.server;

import java.util.*;

public class Database {

    Map<String, Connection> connections;
    List<String> clientNames;

    public Database() {
        connections = new HashMap<>();
        clientNames = new ArrayList<>();
    }

    public void add(String name, Connection connection) {
        connections.put(name, connection);
        clientNames.add(name);
    }

    public void remove(String name) {
        connections.remove(name);
        clientNames.remove(name);
    }

    public Map<String, Connection> getConnections() {
        return connections;
    }

    public void setConnections(Map<String, Connection> connections) {
        this.connections = connections;
    }

    public List<String> getClientNames() {
        return clientNames;
    }

    public void setClintNames(List<String> clintNames) {
        this.clientNames = clientNames;
    }
}
