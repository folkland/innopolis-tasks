package part01.lesson10.server;

public class RunServer {

    public static void main(String[] args) {
        System.out.println("Starting server...");
        Server server = new Server();
        server.startServer();
    }
}
