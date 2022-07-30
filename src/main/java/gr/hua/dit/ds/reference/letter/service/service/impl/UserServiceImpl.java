package gr.hua.dit.ds.reference.letter.service.service.impl;

import org.springframework.stereotype.Service;

import gr.hua.dit.ds.reference.letter.service.entity.User;
import gr.hua.dit.ds.reference.letter.service.repository.UserRepository;
import gr.hua.dit.ds.reference.letter.service.service.UserService;

import java.util.List;

/*
@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;

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
            return userRepository.save(user);
        }

    @Override
    public User getUserById(int id) {
            return userRepository.findById(id).get();
        }

    @Override
    public User updateUser(User user) {
            return userRepository.save(user);
        }

    @Override
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
        }

}
*/