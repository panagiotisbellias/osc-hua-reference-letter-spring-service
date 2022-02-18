package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.ReferenceLetterRequest;
import gr.hua.dit.ds.reference.letter.service.entity.Student;
import gr.hua.dit.ds.reference.letter.service.entity.Teacher;
import gr.hua.dit.ds.reference.letter.service.payload.ProfileDto;
import gr.hua.dit.ds.reference.letter.service.payload.ReferenceLetterRequestDto;
import gr.hua.dit.ds.reference.letter.service.repository.ReferenceLetterRequestRepository;
import gr.hua.dit.ds.reference.letter.service.repository.StudentRepository;
import gr.hua.dit.ds.reference.letter.service.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST API Student Controller
 *
 * The REST API Student Controller provides operations for users of type 'Student'
 *
 * @author Panagiotis Bellias
 * @since 2022-02-18
 * @version 1.0
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@Secured("ROLE_STUDENT")
@RequestMapping("/api/app/reference_letter_requests")
public class ApiStudentController {

    @Autowired
    private ReferenceLetterRequestRepository referenceLetterRequestRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * Create Reference Letter Request Method
     * With this method students are able to create a new reference letter request
     * @param referenceLetterRequest, a dto object to represent the data student wants for its new request.
     * @param authentication, is an object to take information for the current session
     *      *                       using the Authentication autowired bean
     * @return the instance of this new reference letter request
     * @todo add the other attributes, possibly change the db schema and test it with postman and frontend
     */
    @PostMapping("/")
    public ReferenceLetterRequest createRLrequest(@Validated
            @RequestBody ReferenceLetterRequestDto referenceLetterRequest, Authentication authentication) {
        ReferenceLetterRequest rl = new ReferenceLetterRequest();
        String username = authentication.getName();
        Student student = studentRepository.findStudentByUser(username);
        rl.setStudent(student);
        Teacher teacher = teacherRepository.getById(referenceLetterRequest.getTeacherId());
        rl.setTeacher(teacher);
        rl.setCarrierName(referenceLetterRequest.getCarrierName());
        rl.setCarrierEmail(referenceLetterRequest.getCarrierEmail());
        return referenceLetterRequestRepository.save(rl);
    }

    @GetMapping("/")
    public ArrayList<ReferenceLetterRequestDto> getRLrequests() {
        // TODO: view his own rl requests and find the rest info doing matches with students, teachers
        ArrayList<ReferenceLetterRequest> list = (ArrayList<ReferenceLetterRequest>) referenceLetterRequestRepository.findAll();
        ArrayList<ReferenceLetterRequestDto> result = new ArrayList<>();

        for (ReferenceLetterRequest rl : list) {
            ReferenceLetterRequestDto rl_dto = new ReferenceLetterRequestDto();
            rl_dto.setCarrierName(rl.getCarrierName());
            rl_dto.setCarrierEmail(rl.getCarrierEmail());
            result.add(rl_dto);
        }

        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReferenceLetterRequestDto> getMoreInfo(@PathVariable(value = "id") Integer id) {
        // TODO: view more about a rl request, fix it to see basic details for involved student and teacher
        Optional<ReferenceLetterRequest> referenceLetterRequest = referenceLetterRequestRepository.findById(id);

        if (referenceLetterRequest.isPresent()) {
            ReferenceLetterRequestDto referenceLetterRequestDto = new ReferenceLetterRequestDto();
            referenceLetterRequestDto.setCarrierName(referenceLetterRequest.get().getCarrierName());
            referenceLetterRequestDto.setCarrierEmail(referenceLetterRequest.get().getCarrierEmail());
            return ResponseEntity.ok().body(referenceLetterRequestDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public void updateRLrequest(@RequestBody ReferenceLetterRequest referenceLetterRequest) {
        // TODO: update a rl request
    }

    @DeleteMapping("/{id}")
    public void deleteRLrequest() {
        // TODO: delete a rl request
    }
}