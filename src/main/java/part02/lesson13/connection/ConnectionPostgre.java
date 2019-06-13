package part02.lesson13.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * IConnection for PostgreSQL realization
 * @author folkland
 */
public class ConnectionPostgre implements IConnection {

    private Connection connection;

    public ConnectionPostgre(String dbURL, String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(dbURL, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    /**
     * Close connection
     * @return
     */
    @Override
    public boolean close() {
        try {
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
