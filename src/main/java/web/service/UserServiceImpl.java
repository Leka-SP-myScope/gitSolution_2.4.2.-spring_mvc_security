package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public Optional<User> getUserByName(String name) {
        Optional<User> optionalUser = userDao.getUserByName(name);
        if (optionalUser.isPresent() && name != null) {
            return userDao.getUserByName(name);
        }
        throw new NoResultException("No User by: " + name + " present");
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isPresent() && id != null) {
            return optionalUser.get();
        }
        throw new NoResultException("No User by: " + id + " present");
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}