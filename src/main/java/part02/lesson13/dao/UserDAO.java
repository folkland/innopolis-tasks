package part02.lesson13.dao;

import part02.lesson13.entity.User;

import java.util.List;

/**
 * DAO for User
 * @author folkland
 */
public interface UserDAO {

    List<User> getUsers();

    boolean addUser(User user);

    boolean addUsers(List<User> users);

    User getUser(int loginId, String name);
}
