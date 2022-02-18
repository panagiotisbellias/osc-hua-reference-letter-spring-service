package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="students")
public interface StudentRepository extends JpaRepository<Student, Integer>{
    @Query(
            value = "SELECT * FROM students WHERE user = ?1",
            nativeQuery = true)
    Student findStudentByUser(String username);
}
