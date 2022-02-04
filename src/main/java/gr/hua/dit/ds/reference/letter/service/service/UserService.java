package gr.hua.dit.ds.reference.letter.service.service;

import gr.hua.dit.ds.reference.letter.service.entity.User;
import gr.hua.dit.ds.reference.letter.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.*;

import java.util.Collections;


public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private final CustomPasswordEncoder customPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Lazy CustomPasswordEncoder customPasswordEncoder){
        this.userRepository = userRepository;
        this.customPasswordEncoder = customPasswordEncoder;

    }

    public void registerUser(User user) {

        System.out.println("In save =================");
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(customPasswordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);

    }

    public User findUserById(String username) {
        return userRepository.findById(username)
                .orElseThrow(() -> new NullPointerException("user not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Collections.emptyList());

    }
}