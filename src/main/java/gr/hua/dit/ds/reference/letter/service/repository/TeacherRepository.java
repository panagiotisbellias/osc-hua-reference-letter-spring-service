package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.Teacher;
import gr.hua.dit.ds.reference.letter.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path="teachers")
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
    Optional<Teacher> findByEmail(String email);
    Boolean existsByEmail(String email);
}
