package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.ReferenceLetterRequest;
import org.springframework.web.bind.annotation.*;

// REST API
@RestController
@RequestMapping("/reference_letter_requests")
public class RestApiController {

    @PostMapping("/")
    public void createRLrequest(@RequestBody ReferenceLetterRequest referenceLetterRequest){
        // TODO: student creates new rl request
    }

    @GetMapping("/")
    public void getRLrequests(){
        // TODO: view his own rl requests
    }

    @GetMapping("/{id}")
    public void getMoreInfo(){
        // TODO: view more about a rl request
    }

    @PutMapping("/{id}")
    public void updateRLrequest(@RequestBody ReferenceLetterRequest referenceLetterRequest){
        // TODO: update a rl request
    }

    @DeleteMapping("/{id}")
    public void deleteRLrequest(){
        // TODO: delete a rl request
    }

    @GetMapping("/pending")
    public void viewPendingRLrequests(){
        // TODO: view his pending rl requests
    }

    @GetMapping("/pending/{id}")
    public void viewMoreAboutPendingRLrequest(){
        // TODO: view more about his pending rl request
    }

    @PostMapping("/pending/{id}")
    public void approveRLrequest(){
        // TODO: approve a rl request
    }

    @DeleteMapping("/pending/{id}")
    public void declineRLrequest(){
        // TODO: decline a rl request
    }

}
