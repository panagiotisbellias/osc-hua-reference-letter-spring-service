package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.ReferenceLetterRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

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

    @Query(
            value = "SELECT * FROM reference_letter_requests WHERE id=?1 AND id_student = ?2",
            nativeQuery = true)
    Optional<ReferenceLetterRequest> findReferenceLetterRequestByStudent(int rl_request, int student);
}
