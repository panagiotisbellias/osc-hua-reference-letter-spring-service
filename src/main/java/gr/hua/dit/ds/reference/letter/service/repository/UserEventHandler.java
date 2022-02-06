package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RepositoryEventHandler
public class UserEventHandler {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @HandleBeforeCreate
    public void handleUserCreate(User user, String authority) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Authorities auth = new Authorities(authority, user);
        userRepository.save(user);
        authRepository.save(auth);
    }

    @HandleBeforeSave
    public void handleUserUpdate(User user) {
        if (user.getPassword() == null || user.getPassword().equals("")) {
            //keeps the last password
            Optional<User> storedUser = userRepository.findByUsername(user.getUsername());
            storedUser.ifPresent(value -> user.setPassword(value.getPassword()));
        }
        else {
            //password change request
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }
}