package gr.springapp.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.springapp.userservice.domain.User;

public interface UserRepo extends JpaRepository<User, Long>{
    User findByUsername(String username);
    void deleteByUsername(String username);
}
