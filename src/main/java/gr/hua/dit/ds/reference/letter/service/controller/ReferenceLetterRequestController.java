package gr.hua.dit.ds.reference.letter.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gr.hua.dit.ds.reference.letter.service.entity.ReferenceLetterRequest;
import gr.hua.dit.ds.reference.letter.service.repository.ReferenceLetterRequestRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/reference_letter_requests")
public class ReferenceLetterRequestController {

    @Autowired
    private ReferenceLetterRequestRepository referenceLetterRequestRepository;

    @GetMapping("/")
    public List<ReferenceLetterRequest> retrieveAllRequests() {
        return referenceLetterRequestRepository.findAll();
    }

    /* !FILTER ALL BY THE ID STUDENT HAS TO TAKE ONLY HIS REQUESTS
    @GetMapping("/my_requests/{id_student}")
    public List<ReferenceLetterRequest> retrieveMyRequests(//Model model
        @PathVariable int id_student) {
        Optional<ReferenceLetterRequest> referenceLetterRequestList = referenceLetterRequestRepository.findById(id_student);

        if (referenceLetterRequestList.isEmpty())
            throw new ReferenceLetterRequestNotFoundException("id-student-" + id_student);

        return referenceLetterRequestList.get();
        //List<ReferenceLetterRequest> referenceLetterRequests = referenceLetterRequestRepository.findAll();
        // model.addAttribute("referenceLetterRequests", referenceLetterRequests);
        // return "myApplications";
    }*/

    @GetMapping("/{id}")
    public ReferenceLetterRequest retrieveRequest(@PathVariable int id) {
        Optional<ReferenceLetterRequest> referenceLetterRequest = referenceLetterRequestRepository.findById(id);

        if (referenceLetterRequest.isEmpty())
            throw new ReferenceLetterRequestNotFoundException("id-" + id);

        return referenceLetterRequest.get();
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable int id) {
        referenceLetterRequestRepository.deleteById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createRequest(@RequestBody ReferenceLetterRequest referenceLetterRequest) {
        ReferenceLetterRequest savedRequest = referenceLetterRequestRepository.save(referenceLetterRequest);
        System.out.println("reference letter request id " + savedRequest.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedRequest.getId()).toUri();

        return ResponseEntity.created(location).build();
        // return "newApplication";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRequest(@RequestBody ReferenceLetterRequest referenceLetterRequest, @PathVariable int id) {

        Optional<ReferenceLetterRequest> referenceLetterRequestOptional = referenceLetterRequestRepository.findById(id);

        if (referenceLetterRequestOptional.isEmpty())
            return ResponseEntity.notFound().build();

        referenceLetterRequest.setId(id);

        referenceLetterRequestRepository.save(referenceLetterRequest);

        return ResponseEntity.noContent().build();

    }

    // SAME AS retrieveMyRequests, WE HAVE TO TAKE THE REQUESTS ACCORDING TO TEACHER'S ID AND pending=true
    // MAYBE db schema changes
    // @RequestMapping("/see_pending")
    // public String seePending() {
       // return "seePending";
    // }

}
