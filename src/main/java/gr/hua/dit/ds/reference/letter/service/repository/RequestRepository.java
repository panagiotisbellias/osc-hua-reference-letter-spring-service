package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.ReferenceLetterRequest;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource(path="requests")
public interface RequestRepository extends JpaRepository<ReferenceLetterRequest, Integer>{
}
