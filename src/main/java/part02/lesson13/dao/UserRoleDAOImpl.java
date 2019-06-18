package part02.lesson13.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import part02.lesson13.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Realization user role DAO
 * @author folkland
 */
public class UserRoleDAOImpl implements UserRoleDAO {

    private final Connection connection;
    private Logger logger = LogManager.getLogger(UserRoleDAOImpl.class);

    private final String SELECT_USER_ROLES = "select id, user_id, role_id from inno.user_role";
    private final String INSERT_USER_ROLE = "insert into inno.user_role(id, user_id, role_id) values (?, ?, ?)";
    private final String UPDATE_USER_ROLE = "update inno.user_role set role_id = ? where user_id = ?";

    public UserRoleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Get all user roles
     * @return
     */
    @Override
    public List<UserRole> getUsersRole() {
        List<UserRole> userRoles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USER_ROLES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserRole userRole = new UserRole();
                userRole.setId(resultSet.getInt(1));
                userRole.setUserId(resultSet.getInt(2));
                userRole.setRoleId(resultSet.getInt(3));
                userRoles.add(userRole);
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            logger.error("getUsersRole", e);
        }
        return userRoles;
    }

    /**
     * change role for one user
     * @param userId
     * @param roleId
     * @return
     */
    @Override
    public boolean changeUserRole(int userId, int roleId) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_ROLE)) {
            statement.setInt(1, roleId);
            statement.setInt(2, userId);
            statement.execute();
            return true;
        } catch (SQLException e) {
//            e.printStackTrace();
            logger.error("changeUserRole", e);
            return false;
        }
    }

    /**
     * add new user role
     * @param userRole
     * @return
     */
    @Override
    public boolean addUserRole(UserRole userRole) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER_ROLE)) {
            statement.setInt(1, userRole.getId());
            statement.setInt(2, userRole.getUserId());
            statement.setInt(3, userRole.getRoleId());
            statement.execute();
            return true;
        } catch (SQLException e) {
//            e.printStackTrace();
            logger.error("addUserRole", e);
            return false;
        }
    }
}
