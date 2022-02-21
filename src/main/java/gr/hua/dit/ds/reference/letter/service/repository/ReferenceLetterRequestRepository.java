package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.ReferenceLetterRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path="requests")
public interface ReferenceLetterRequestRepository extends CrudRepository<ReferenceLetterRequest, Integer> {
    @Query(
            value = "SELECT * FROM reference_letter_requests WHERE id_student = ?1",
            nativeQuery = true)
    List<ReferenceLetterRequest> findReferenceLetterRequestsByStudent(int student);

    @Query(
            value = "SELECT * FROM reference_letter_requests WHERE is_pending AND id_teacher = ?1",
            nativeQuery = true)
    List<ReferenceLetterRequest> findPendingReferenceLetterRequestsByTeacher(int teacher);
}
