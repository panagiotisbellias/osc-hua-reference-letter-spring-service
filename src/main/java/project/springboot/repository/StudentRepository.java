package project.springboot.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource(path="students")
public interface StudentRepository extends JpaRepository<Student, Integer>{
}
