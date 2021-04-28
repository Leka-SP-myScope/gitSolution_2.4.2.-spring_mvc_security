package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.model.User;
import web.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userCandidate = userService.getUserByName(username);
        if (userCandidate == null) {
            throw new UsernameNotFoundException("The entered user is incorrect: " + username);
        }
        return new org.springframework.security.core.userdetails.User(userCandidate.getName(),
                userCandidate.getPassword(), userCandidate.getAuthorities());
    }
}