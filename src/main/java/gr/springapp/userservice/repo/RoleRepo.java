package gr.springapp.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import gr.springapp.userservice.domain.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
    Role findByName(String name);
}
