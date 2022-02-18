package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.Course;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource(path="courses")
public interface CourseRepository extends JpaRepository<Course, Integer>{
}
