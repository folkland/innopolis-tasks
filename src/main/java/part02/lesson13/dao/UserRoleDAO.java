package part02.lesson13.dao;

import part02.lesson13.entity.UserRole;

import java.util.List;

/**
 * DAO for user role
 * @author folkland
 */
public interface UserRoleDAO {

    List<UserRole> getUsersRole();

    boolean addUserRole(UserRole userRole);

    boolean changeUserRole(int userId, int roleId);
}
