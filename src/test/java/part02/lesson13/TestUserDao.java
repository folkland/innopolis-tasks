package part02.lesson13;

import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockFileDatabase;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import part02.lesson13.dao.UserDAO;
import part02.lesson13.dao.UserDAOImpl;
import part02.lesson13.entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUserDao {

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
    public void testGetUsers() {
        UserDAO userDAO = new UserDAOImpl(connection);
        List<User> users = userDAO.getUsers();
        for (User user: users) {
            System.out.println(user.toString());
        }
        Assert.assertEquals(4, users.size());
    }

    @Test
    public void testGetUser() {
        UserDAO userDAO = new UserDAOImpl(connection);
        User user1r = createUser(1, "John", new Date(1561458576), 10, "Kazan", "some@some.com", "some user");
        User user2r = createUser(1, "John", new Date(1561458576), 20, "Kazan", "some@some.com", "some user");
        User user1b = userDAO.getUser(10, "John");
        System.out.println(user1b.toString());
        User user2b = userDAO.getUser(20, "John");
        System.out.println(user2b.toString());
        Assert.assertEquals(user1r, user1b);
        Assert.assertEquals(user2r, user2b);
    }

    @Test
    public void testInsertUser() {
        UserDAO userDAO = new UserDAOImpl(connection);
        User user = createUser(9999, "Lana", new Date(1561458576), 15, "Kazan", "some@some.com", "some_user");
        Assert.assertEquals(true, userDAO.addUser(user));
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        Assert.assertEquals(true, userDAO.addUsers(users));
    }

    /**
     * Create user
     * @param id
     * @param name
     * @param birthday
     * @param loginId
     * @param city
     * @param email
     * @param description
     * @return
     */
    private User createUser(int id, String name, Date birthday, int loginId, String city, String email, String description) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setBirthday(birthday);
        user.setLoginId(loginId);
        user.setCity(city);
        user.setEmail(email);
        user.setDescription(description);
        return user;
    }
}
