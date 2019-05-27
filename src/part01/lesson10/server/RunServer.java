package part01.lesson10.server;

/**
 * Main server instance
 *
 * @author folkland
 */
public class RunServer {

    public static void main(String[] args) {
        System.out.println("Starting server...");
        Server server = new Server();
        server.startServer();
    }
}
