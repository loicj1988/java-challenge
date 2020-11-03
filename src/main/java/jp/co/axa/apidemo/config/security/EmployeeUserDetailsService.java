package jp.co.axa.apidemo.config.security;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring user service class
 * 
 * Used to load users in Spring framework
 * 
 * @author Loic
 * @version 0.0.1
 *
 */
@Service
public class EmployeeUserDetailsService implements UserDetailsService {

  /**
   * Load user into spring by username
   * 
   * @param username The username to load
   * @return Object containing user details
   * @throws UsernameNotFoundException when user is not found
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return new User("user1", "pass1", new ArrayList<>());
  }

}
