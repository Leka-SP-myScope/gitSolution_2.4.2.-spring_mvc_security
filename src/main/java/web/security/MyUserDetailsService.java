package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public MyUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    //@Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userCandidate = userDao.getUserByName(username);
        if(userCandidate == null) {
            throw new UsernameNotFoundException("The entered user is incorrect: " + username);
        }
        return new org.springframework.security.core.userdetails.User(userCandidate.getName(),
                userCandidate.getPassword(), userCandidate.getAuthorities());
    }
}