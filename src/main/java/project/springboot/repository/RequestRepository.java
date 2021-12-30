package project.springboot.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import project.springboot.entity.ReferenceLetterRequest;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource(path="requests")
public interface RequestRepository extends JpaRepository<ReferenceLetterRequest, Integer>{
}
