package gr.hua.dit.ds.reference.letter.service.service;

import gr.hua.dit.ds.reference.letter.service.entity.Authorities;
import gr.hua.dit.ds.reference.letter.service.entity.User;
import gr.hua.dit.ds.reference.letter.service.repository.AuthRepository;
import gr.hua.dit.ds.reference.letter.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    public void registerUser(User user, @Nullable String authority) {

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setEnabled(1); // enabled
        Authorities auth1 = new Authorities("ROLE_USER", newUser);
        userRepository.save(newUser);
        authRepository.save(auth1);
        if (authority != null) {
            Authorities auth2 = new Authorities(authority, newUser);
            authRepository.save(auth2);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
    }

}
