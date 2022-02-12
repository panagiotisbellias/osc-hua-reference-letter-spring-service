package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="teachers")
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
    Teacher findTeacherByUser(User user);
}
