package part02.lesson13.connection;

import java.sql.Connection;

/**
 * Interface for connection
 * @author folkland
 */
public interface IConnection {

    Connection getConnection();

    boolean close();
}
