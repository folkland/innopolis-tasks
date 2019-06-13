package part02.lesson13.dao;

import part02.lesson13.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Realization for user DAO
 * @author folkland
 */
public class UserDAOImpl implements UserDAO {

    private final Connection connection;

    private final String SELECT_ALL_USER = "select id, name, birthday, login_id, city, email, description from inno.user";
    private final String INSERT_USER = "insert into inno.user(id, name, birthday, login_id, city, email, description) values(?,?,?,?,?,?,?)";
    private final String SELECT_USER = " where login_id = ? and name = ?";

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * Get all user from base
     * @return
     */
    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setBirthday(new Date(resultSet.getLong(3)));
                user.setLoginId(resultSet.getInt(4));
                user.setCity(resultSet.getString(5));
                user.setEmail(resultSet.getString(6));
                user.setDescription(resultSet.getString(7));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Add a few user, used batch process
     * @param users
     * @return
     */
    @Override
    public boolean addUsers(List<User> users) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            for (User user: users) {
                statement.setInt(1, user.getId());
                statement.setString(2, user.getName());
                statement.setLong(3, user.getBirthday().getTime());
                statement.setInt(4, user.getLoginId());
                statement.setString(5, user.getCity());
                statement.setString(6, user.getEmail());
                statement.setString(7, user.getDescription());
                statement.addBatch();
            }
            statement.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Add user to base
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        List<User> users = new ArrayList<>();
        users.add(user);
        return addUsers(users);
    }

    /**
     * Get only one user
     * @param loginId
     * @param name
     * @return
     */
    @Override
    public User getUser(int loginId, String name) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER + SELECT_USER)) {
            statement.setInt(1, loginId);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setBirthday(new Date(resultSet.getLong(3)));
            user.setLoginId(resultSet.getInt(4));
            user.setCity(resultSet.getString(5));
            user.setEmail(resultSet.getString(6));
            user.setDescription(resultSet.getString(7));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
