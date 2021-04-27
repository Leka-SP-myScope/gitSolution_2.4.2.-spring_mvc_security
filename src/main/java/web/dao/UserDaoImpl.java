package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        Optional<User> optionalUser = Optional.of(query.getSingleResult());
        return optionalUser.get();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(Long id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        User user = query.getResultList().stream().findAny().orElse(null);
        entityManager.remove(user);
    }

    @Override
    public User getUserByName(String name) {
        userMap.put("user",
                new User(1L, "user", "Userov", "user", 18,
                        Collections.singleton(new Role(1L, "ROLE_USER"))));
        userMap.put("admin",
                new User(2L, "admin", "Adminov", "admin", 23,
                        Collections.singleton(new Role(2L, "ROLE_ADMIN"))));
        if (!userMap.containsKey(name)) {
            return null;
        }
        return userMap.get(name);
    }
}