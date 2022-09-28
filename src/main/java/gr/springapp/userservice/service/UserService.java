package gr.springapp.userservice.service;

import java.util.List;

import gr.springapp.userservice.domain.ReferenceLetterRequest;
import gr.springapp.userservice.domain.Role;
import gr.springapp.userservice.domain.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    void deleteUser(String username);
    List<ReferenceLetterRequest> getReferenceLetterRequests();
    ReferenceLetterRequest saveReferenceLetterRequest(ReferenceLetterRequest referenceLetterRequest);
    List<ReferenceLetterRequest> getReferenceLetterRequestsByUser(String senderUsername);
    List<ReferenceLetterRequest> getPendingRequests(String username, String state);
    ReferenceLetterRequest approveReferenceLetterRequest(Long  id, String text);
    ReferenceLetterRequest declineReferenceLetterRequest(Long id);
}
