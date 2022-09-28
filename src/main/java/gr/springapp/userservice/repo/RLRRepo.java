package gr.springapp.userservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.springapp.userservice.domain.ReferenceLetterRequest;

public interface RLRRepo extends JpaRepository<ReferenceLetterRequest, Long>{
    List<ReferenceLetterRequest> findBySenderUsername(String senderUsername);
    List<ReferenceLetterRequest> findByReciverUsername(String reciverUsername);
    List<ReferenceLetterRequest> findByState(String state);
}
