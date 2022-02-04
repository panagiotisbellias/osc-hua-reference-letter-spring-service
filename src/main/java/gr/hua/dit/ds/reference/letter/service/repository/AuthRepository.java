package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.Authorities;
import gr.hua.dit.ds.reference.letter.service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path="authorities")
public interface AuthRepository extends JpaRepository<Authorities, String>{
    Optional<Authorities> findByAuthority(String authority);
}
