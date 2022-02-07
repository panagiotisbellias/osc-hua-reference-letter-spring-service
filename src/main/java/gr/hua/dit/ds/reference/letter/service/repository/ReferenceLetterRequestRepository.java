package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.ReferenceLetterRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="requests")
public interface ReferenceLetterRequestRepository extends CrudRepository<ReferenceLetterRequest, Integer> {
}
