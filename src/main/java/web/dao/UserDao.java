package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUser();

    User getUserByName(String name);

    User findById(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteById(Long id);
}