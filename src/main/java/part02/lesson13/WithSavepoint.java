package part02.lesson13;

import part02.lesson13.dao.*;
import part02.lesson13.entity.Role;
import part02.lesson13.entity.User;
import part02.lesson13.entity.UserRole;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author folkland
 */
public class WithSavepoint {

    private Connection connection;

    public WithSavepoint(Connection connection) {
        this.connection = connection;
    }

    void insertsWithoutTrouble() {
        Savepoint savepoint = null;
        RoleDAO roleDAO = new RoleDAOImpl(connection);
        try {
            connection.setAutoCommit(false);
            Role role = new Role();
            role.setId(1);
            role.setName("usual");
            role.setDescription("Role for usual user, without privileges");
            roleDAO.addRole(role);
            savepoint = connection.setSavepoint("A");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Role role = new Role();
            role.setId(2);
            role.setName("admin");
            role.setDescription("Role for super user, with privileges");
            roleDAO.addRole(role);
            savepoint = connection.setSavepoint("B");
        } catch (SQLException e) {
            e.printStackTrace();
            if (savepoint != null) {
                try {
                    connection.rollback(savepoint);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void insertsWithTrouble() {
        Savepoint savepoint = null;
        UserDAO userDAO = new UserDAOImpl(connection);
        UserRoleDAO userRoleDAO = new UserRoleDAOImpl(connection);
        try {
            connection.setAutoCommit(false);
            User user = createUser(1, "John", new Date(), 10, "Kazan", "some@some.com", "some user");
            userDAO.addUser(user);
            userRoleDAO.addUserRole(createUserRole(1, user.getId(), 1));
            savepoint = connection.setSavepoint("A");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            List<User> users = new ArrayList<>();
            users.add(createUser(2, "Jane", new Date(), 10, "Kazan", "some@some.com", "some user"));
            users.add(createUser(3, "Jack", new Date(), 10, "Kazan", "some@some.com", "some user"));
            users.add(createUser(4, "Judy", new Date(), 10, "Kazan", "some@some.com", "some user"));
            userDAO.addUsers(users);
            int k = 2;
            for (User user: users) {
                userRoleDAO.addUserRole(createUserRole(k, user.getId(), 1));
                k++;
            }
            savepoint = connection.setSavepoint("B");
        } catch (SQLException e) {
            e.printStackTrace();
            if (savepoint != null) {
                try {
                    connection.rollback(savepoint);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        try {
            User user = createUser(1, "Nobody", new Date(), 10, "Kazan", "some@some.com", "some user");
            userDAO.addUser(user);
            userRoleDAO.addUserRole(createUserRole(1, user.getId(), 1));
            savepoint = connection.setSavepoint("C");
        } catch (SQLException e) {
            e.printStackTrace();
            if (savepoint != null) {
                try {
                    connection.rollback(savepoint);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
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
    private UserRole createUserRole(int id, int userId, int roleId) {
        UserRole userRole = new UserRole();
        userRole.setId(id);
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return userRole;
    }

    void showRoles() {
        RoleDAO roleDAO = new RoleDAOImpl(connection);
        List<Role> roles = roleDAO.getRoles();
        for (Role role: roles) {
            System.out.println(role.toString());
        }
    }

    void showUsers() {
        UserDAO userDAO = new UserDAOImpl(connection);
        List<User> users = userDAO.getUsers();
        for (User user: users) {
            System.out.println(user.toString());
        }
    }

    void showUserRoles() {
        UserRoleDAO userRoleDAO = new UserRoleDAOImpl(connection);
        List<UserRole> userRoles = userRoleDAO.getUsersRole();
        for (UserRole userRole: userRoles) {
            System.out.println(userRole.toString());
        }
    }

    void getUser(String name, int loginId) {
        UserDAO userDAO = new UserDAOImpl(connection);
        System.out.println(userDAO.getUser(loginId, name));
    }
}
