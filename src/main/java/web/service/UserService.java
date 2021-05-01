package web.service;

import web.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUser();

    Optional<User> getUserByName(String name);

    User findById(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteById(Long id);
}