package gr.hua.dit.ds.reference.letter.service.service;

import gr.hua.dit.ds.reference.letter.service.entity.Authorities;
import gr.hua.dit.ds.reference.letter.service.entity.User;
import gr.hua.dit.ds.reference.letter.service.repository.AuthRepository;
import gr.hua.dit.ds.reference.letter.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    public void registerUser(User user, String authority) {

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setEnabled(1); // enabled

        Authorities auth = new Authorities(authority, newUser);
        newUser.addAuthority(auth);

        userRepository.save(newUser);
        authRepository.save(auth);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),  user.get().getAuthorities());
        }else{
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }

    }

}
