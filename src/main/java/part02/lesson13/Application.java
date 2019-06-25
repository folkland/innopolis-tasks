package part02.lesson13;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part02.lesson13.connection.ConnectionPostgre;

import java.sql.Connection;

/**
 * Application class for starting code
 * @author folkland
 */
public class Application {

    private static Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        ConnectionPostgre postgre = new ConnectionPostgre();
        Connection connection = postgre.getConnection();
        logger.info("Connection created");
        WithSavepoint withSavepoint = new WithSavepoint(connection);
        withSavepoint.insertsWithoutTrouble();
        withSavepoint.insertsWithTrouble();
        logger.info("All inserts done");
        withSavepoint.showRoles();
        withSavepoint.showUsers();
        withSavepoint.showUserRoles();
        logger.info("All data from db showed");
        withSavepoint.getUser("John", 10);
        postgre.close();
        logger.info("Connection closed");
    }
}
