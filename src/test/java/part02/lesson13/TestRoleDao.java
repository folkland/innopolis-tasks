package part02.lesson13;

import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockFileDatabase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import part02.lesson13.dao.RoleDAO;
import part02.lesson13.dao.RoleDAOImpl;
import part02.lesson13.entity.Role;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestRoleDao {

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
    public void testGetRoles() {
        RoleDAO roleDAO = new RoleDAOImpl(connection);
        List<Role> roles = roleDAO.getRoles();
        for (Role role: roles) {
            System.out.println(role.toString());
        }
        Assert.assertEquals(2, roles.size());
    }

    @Test
    public void testInsertRole() {
        RoleDAO roleDAO = new RoleDAOImpl(connection);
        Role role = new Role();
        role.setId(9999);
        role.setName("test");
        role.setDescription("Testing");
        Assert.assertEquals(true, roleDAO.addRole(role));
    }
}
