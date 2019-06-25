package part02.lesson13;

import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockFileDatabase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import part02.lesson13.dao.UserRoleDAO;
import part02.lesson13.dao.UserRoleDAOImpl;
import part02.lesson13.entity.UserRole;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestUserRolesDao {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try {
            MockDataProvider provider = new MockFileDatabase(TestDBConnection.class.getResourceAsStream("/lesson13/myDBtest.txt"));
            connection = new MockConnection(provider);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUserRoles() {
        UserRoleDAO roleDAO = new UserRoleDAOImpl(connection);
        List<UserRole> roles = roleDAO.getUsersRole();
        for (UserRole role: roles) {
            System.out.println(role.toString());
        }
        Assert.assertEquals(4, roles.size());
    }

    @Test
    public void testInsertUserRoles() {
        UserRoleDAO userRoleDAO = new UserRoleDAOImpl(connection);
        UserRole userRole = new UserRole();
        userRole.setId(9999);
        userRole.setUserId(9999);
        userRole.setRoleId(1);
        Assert.assertEquals(true, userRoleDAO.addUserRole(userRole));
    }

    @Test
    public void testUpdateUserRoles() {
        UserRoleDAO userRoleDAO = new UserRoleDAOImpl(connection);
        Assert.assertEquals(true, userRoleDAO.changeUserRole(9999, 1));
    }
}
