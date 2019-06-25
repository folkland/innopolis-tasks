package part02.lesson13;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * Class which contain other functions to testing code
 * @author folkland
 */
public class WithSavepoint {

    private Connection connection;

    private Logger logger = LogManager.getLogger(WithSavepoint.class);

    WithSavepoint(Connection connection) {
        this.connection = connection;
    }

    /**
     * Use savepoint and in this code not a trouble
     */
    void insertsWithoutTrouble() {
        logger.info("insertsWithoutTrouble started");
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
            logger.info("insertsWithoutTrouble savepoint A done");
        } catch (SQLException e) {
//            e.printStackTrace();
            logger.error("Error in insertsWithoutTrouble, savepoint A", e);
        }
        try {
            Role role = new Role();
            role.setId(2);
            role.setName("admin");
            role.setDescription("Role for super user, with privileges");
            roleDAO.addRole(role);
            savepoint = connection.setSavepoint("B");
            logger.info("insertsWithoutTrouble savepoint B done");
        } catch (SQLException e) {
//            e.printStackTrace();
            logger.error("Error in insertsWithoutTrouble, savepoint B", e);
            if (savepoint != null) {
                try {
                    connection.rollback(savepoint);
                    logger.info("rollback in insertsWithoutTrouble to savepoint A done");
                } catch (SQLException ex) {
//                    ex.printStackTrace();
                    logger.error("Trouble in insertsWithoutTrouble then rollback to savepoint A", ex);
                }
            }
        } finally {
            try {
                connection.commit();
                connection.setAutoCommit(true);
                logger.info("Commit in insertsWithoutTrouble done");
            } catch (SQLException e) {
//                e.printStackTrace();
                logger.error("Trouble in insertsWithoutTrouble then commit", e);
            }
        }
    }

    /**
     * Use savepoint but in this code have a trouble
     */
    void insertsWithTrouble() {
        logger.info("insertsWithTrouble started");
        Savepoint savepoint = null;
        UserDAO userDAO = new UserDAOImpl(connection);
        UserRoleDAO userRoleDAO = new UserRoleDAOImpl(connection);
        try {
            connection.setAutoCommit(false);
            User user = createUser(1, "John", new Date(), 10, "Kazan", "some@some.com", "some user");
            userDAO.addUser(user);
            userRoleDAO.addUserRole(createUserRole(1, user.getId(), 1));
            savepoint = connection.setSavepoint("A");
            logger.info("insertsWithTrouble savepoint A done");
        } catch (SQLException e) {
//            e.printStackTrace();
            logger.error("Error in insertsWithTrouble, savepoint A", e);
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
            logger.info("insertsWithTrouble savepoint B done");
        } catch (SQLException e) {
//            e.printStackTrace();
            logger.error("Error in insertsWithTrouble, savepoint B", e);
            if (savepoint != null) {
                try {
                    connection.rollback(savepoint);
                    logger.info("rollback in insertsWithTrouble to savepoint A done");
                } catch (SQLException ex) {
//                    ex.printStackTrace();
                    logger.error("Trouble in insertsWithTrouble then rollback to savepoint A", ex);
                }
            }
        }
        try {
            User user = createUser(1, "Nobody", new Date(), 10, "Kazan", "some@some.com", "some user");
            userDAO.addUser(user);
            userRoleDAO.addUserRole(createUserRole(1, user.getId(), 1));
            savepoint = connection.setSavepoint("C");
            logger.info("insertsWithTrouble savepoint C done");
        } catch (SQLException e) {
//            e.printStackTrace();
            logger.error("Error in insertsWithTrouble, savepoint C", e);
            if (savepoint != null) {
                try {
                    connection.rollback(savepoint);
                    logger.info("rollback in insertsWithTrouble to savepoint B done");
                } catch (SQLException ex) {
//                    ex.printStackTrace();
                    logger.error("Trouble in insertsWithTrouble then rollback to savepoint B", ex);
                }
            }
        } finally {
            try {
                connection.commit();
                connection.setAutoCommit(true);
                logger.info("Commit in insertsWithTrouble done");
            } catch (SQLException e) {
//                e.printStackTrace();
                logger.error("Trouble in insertsWithTrouble then commit", e);
            }
        }
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

    /**
     * Create user role
     * @param id
     * @param userId
     * @param roleId
     * @return
     */
    private UserRole createUserRole(int id, int userId, int roleId) {
        UserRole userRole = new UserRole();
        userRole.setId(id);
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return userRole;
    }

    /**
     * Show in console all roles from base
     */
    void showRoles() {
        RoleDAO roleDAO = new RoleDAOImpl(connection);
        List<Role> roles = roleDAO.getRoles();
        for (Role role: roles) {
            System.out.println(role.toString());
        }
    }

    /**
     * Show in console all users from base
     */
    void showUsers() {
        UserDAO userDAO = new UserDAOImpl(connection);
        List<User> users = userDAO.getUsers();
        for (User user: users) {
            System.out.println(user.toString());
        }
    }

    /**
     * Show in console all user roles from base
     */
    void showUserRoles() {
        UserRoleDAO userRoleDAO = new UserRoleDAOImpl(connection);
        List<UserRole> userRoles = userRoleDAO.getUsersRole();
        for (UserRole userRole: userRoles) {
            System.out.println(userRole.toString());
        }
    }

    /**
     * Show in console only one user from base
     * @param name
     * @param loginId
     */
    void getUser(String name, int loginId) {
        UserDAO userDAO = new UserDAOImpl(connection);
        System.out.println(userDAO.getUser(loginId, name));
    }
}
