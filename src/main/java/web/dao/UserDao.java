package web.dao;

import java.util.List;

public interface UserDao {

    List<User> getAllUser();

    User findById(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteById(Long id);
}