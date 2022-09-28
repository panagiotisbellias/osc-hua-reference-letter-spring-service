package gr.springapp.userservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
// import java.util.stream.Stream;
// import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.springapp.userservice.domain.ReferenceLetterRequest;
import gr.springapp.userservice.domain.Role;
import gr.springapp.userservice.domain.User;
import gr.springapp.userservice.repo.RLRRepo;
import gr.springapp.userservice.repo.RoleRepo;
import gr.springapp.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService{
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final RLRRepo rlrRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepo.findByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user){
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user); 
    }

    @Override
    public Role saveRole(Role role){
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName){
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username){
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers(){
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public void deleteUser(String username) {
        log.info("Deleting user {}", username);
        userRepo.deleteByUsername(username);
    }

    @Override
    public ReferenceLetterRequest saveReferenceLetterRequest(ReferenceLetterRequest referenceLetterRequest) {
        log.info("Saving reference letter request: {}\nWith sender: {}\nAnd reciver: {}", referenceLetterRequest.getTitle(), referenceLetterRequest.getSenderUsername(), referenceLetterRequest.getReciverUsername());
        return rlrRepo.save(referenceLetterRequest);
    }

    @Override
    public List<ReferenceLetterRequest> getReferenceLetterRequestsByUser(String username) {
        log.info("Fetching all reference letter requests for user {}", username);
        List<ReferenceLetterRequest> rlrList = new ArrayList<>();
        rlrList = Stream.concat(rlrRepo.findBySenderUsername(username).stream(), rlrRepo.findByReciverUsername(username).stream()).collect(Collectors.toList());
        //return rlrRepo.findBySenderUsername(username);
        return rlrList;
    }

    @Override
    public List<ReferenceLetterRequest> getReferenceLetterRequests() {
        log.info("Fetching all requests");
        return rlrRepo.findAll();
    }

    @Override
    public List<ReferenceLetterRequest> getPendingRequests(String username, String state) {
        log.info("Fetching pending requests of user: {}", username);
        List<ReferenceLetterRequest> userRequests = rlrRepo.findByReciverUsername(username);
        List<ReferenceLetterRequest> pendingRequests = new ArrayList<>();
        for(int i = 0; i < userRequests.size(); i++){
            if(userRequests.get(i).getState().equals(state)){
                pendingRequests.add(userRequests.get(i));
            }
        }
        return pendingRequests;
    }

    @Override
    public ReferenceLetterRequest approveReferenceLetterRequest(Long id, String text) {
        Optional<ReferenceLetterRequest> request = rlrRepo.findById(id);
        log.info("Approving request {} with ID: {}", request.get().getTitle(), request.get().getId());
        request.get().setState("APPROVED");
        request.get().setText(text);
        return saveReferenceLetterRequest(request.get());
    }

    @Override
    public ReferenceLetterRequest declineReferenceLetterRequest(Long id) {
        Optional<ReferenceLetterRequest> request = rlrRepo.findById(id);
        log.info("Declining request {} with ID: {}", request.get().getTitle(), request.get().getId());
        request.get().setState("DECLINED");
        return saveReferenceLetterRequest(request.get());
    }
}
