package gr.hua.dit.ds.reference.letter.service.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gr.hua.dit.ds.reference.letter.service.entity.User;
import gr.hua.dit.ds.reference.letter.service.repository.UserRepository;
import gr.hua.dit.ds.reference.letter.service.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl extends UserService {
    
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
            //System.out.println(teacher);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }

    /*
    @Override
    public User getUserById(int id) {
            return userRepository.findById(id).get();
        }
        */
    @Override
    public User updateUser(User user) {
            return userRepository.save(user);
        }
        /*
    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
        }*/
    @Override
    public Optional<User> getUser(String username){
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

}