package dao;

import java.util.List;

import supereats.User;

public interface UserDAO {
    void createUser(User user);
    User getUserById(int userId);
    User getUserByEmail(String email);
    void updateUser(User user);
    void deleteUser(int userId);
    List<User> getAllUsers();
}
