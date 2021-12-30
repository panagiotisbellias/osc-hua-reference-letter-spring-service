package project.springboot.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.springboot.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource(path="teachers")
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
}
