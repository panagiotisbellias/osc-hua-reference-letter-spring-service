package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.ReferenceLetterRequest;
import gr.hua.dit.ds.reference.letter.service.payload.ReferenceLetterRequestDto;
import gr.hua.dit.ds.reference.letter.service.repository.ReferenceLetterRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// REST API
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@Secured("ROLE_STUDENT")
@RequestMapping("/api/app/reference_letter_requests")
public class ApiStudentController {

    @Autowired
    private ReferenceLetterRequestRepository referenceLetterRequestRepository;

    @PostMapping("/")
    public ReferenceLetterRequest createRLrequest(@Validated
                                                  @RequestBody ReferenceLetterRequestDto referenceLetterRequest) {
        // TODO: --> do matches for the rest data
        ReferenceLetterRequest rl = new ReferenceLetterRequest();
        //rl.setStudent();
        //rl.setTeacher();
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