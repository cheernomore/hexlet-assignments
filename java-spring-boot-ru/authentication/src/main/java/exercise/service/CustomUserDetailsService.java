package exercise.service;

import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

// BEGIN
@Service
public class CustomUserDetailsService implements UserDetailsManager {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Username with email: " + username + "not found")
        );
    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void createUser(UserDetails user) {

    }
}
// END
