package part02.lesson13;

import part02.lesson13.connection.BDConfiguration;
import part02.lesson13.connection.ConnectionPostgre;

import java.sql.Connection;

/**
 * Application class for starting code
 * @author folkland
 */
public class Application {

    public static void main(String[] args) {
        BDConfiguration configuration = new BDConfiguration();
        ConnectionPostgre postgre = new ConnectionPostgre(configuration.getDburl(), configuration.getUsername(), configuration.getPassword());
        Connection connection = postgre.getConnection();
        WithSavepoint withSavepoint = new WithSavepoint(connection);
        withSavepoint.insertsWithoutTrouble();
        withSavepoint.insertsWithTrouble();
        withSavepoint.showRoles();
        withSavepoint.showUsers();
        withSavepoint.showUserRoles();
        withSavepoint.getUser("John", 10);
        postgre.close();
    }
}
