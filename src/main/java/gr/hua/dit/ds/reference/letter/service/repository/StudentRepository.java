package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path="students")
public interface StudentRepository extends JpaRepository<Student, Integer>{
    Optional<Student> findByEmail(String email);
    Boolean existsByEmail(String email);
}
