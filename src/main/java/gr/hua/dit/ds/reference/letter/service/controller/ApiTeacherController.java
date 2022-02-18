package gr.hua.dit.ds.reference.letter.service.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

// REST API
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@Secured("ROLE_TEACHER")
@RequestMapping("/api/app/reference_letter_requests")
public class ApiTeacherController {

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
