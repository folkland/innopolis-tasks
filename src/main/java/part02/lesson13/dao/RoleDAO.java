package part02.lesson13.dao;

import part02.lesson13.entity.Role;

import java.util.List;

/**
 * DAO for role
 * @author folkland
 */
public interface RoleDAO {

    List<Role> getRoles();

    boolean addRole(Role role);
}
