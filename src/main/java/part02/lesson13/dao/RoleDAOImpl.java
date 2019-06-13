package part02.lesson13.dao;

import part02.lesson13.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Realization for role DAO
 * @author folkland
 */
public class RoleDAOImpl implements RoleDAO {

    private final Connection connection;

    private final String SELECT_ROLES = "select id, name, description from inno.role";
    private final String INSERT_ROLE = "insert into inno.role(id, name, description) values (?, ?, ?)";

    public RoleDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ROLES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt(1));
                role.setName(resultSet.getString(2));
                role.setDescription(resultSet.getString(3));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public boolean addRole(Role role) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ROLE)) {
            statement.setInt(1, role.getId());
            statement.setString(2, role.getName());
            statement.setString(3, role.getDescription());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
