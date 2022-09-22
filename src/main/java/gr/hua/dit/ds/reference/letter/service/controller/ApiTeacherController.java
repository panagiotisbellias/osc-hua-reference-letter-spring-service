package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import gr.hua.dit.ds.reference.letter.service.payload.*;
import gr.hua.dit.ds.reference.letter.service.repository.*;
import gr.hua.dit.ds.reference.letter.service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@Secured("ROLE_TEACHER")
@RequestMapping("/api/app/reference_letter_requests")
public class ApiTeacherController {

    @Autowired
    private ReferenceLetterRequestRepository referenceLetterRequestRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/pending")
    public ArrayList<ReferenceLetterRequestDto> viewPendingRLrequests(Authentication authentication){
        String username = authentication.getName();
        Teacher teacher = teacherRepository.findTeacherByUser(username);
        ArrayList<ReferenceLetterRequest> list =
                (ArrayList<ReferenceLetterRequest>) referenceLetterRequestRepository.findPendingReferenceLetterRequestsByTeacher(teacher.getId());
        ArrayList<ReferenceLetterRequestDto> result = new ArrayList<>();

        for (ReferenceLetterRequest rl : list) {
            ReferenceLetterRequestDto rl_dto = new ReferenceLetterRequestDto();
            StudentDto student = new StudentDto();
            student.setFullName(rl.getStudent().getFullName());
            student.setEmail(rl.getStudent().getEmail());
            rl_dto.setId(rl.getId());
            rl_dto.setStudent(student);
            rl_dto.setCarrierName(rl.getCarrierName());
            rl_dto.setCarrierEmail(rl.getCarrierEmail());
            result.add(rl_dto);
        }

        return result;
    }

    @GetMapping("/pending/{id}")
    public ResponseEntity<ReferenceLetterRequestDto> viewMoreAboutPendingRLrequest(@PathVariable(value = "id") Integer id){
        Optional<ReferenceLetterRequest> referenceLetterRequest = referenceLetterRequestRepository.findById(id);

        if (referenceLetterRequest.isPresent()) {
            ReferenceLetterRequestDto referenceLetterRequestDto = new ReferenceLetterRequestDto();
            StudentDto studentDto = new StudentDto();
            Student student = referenceLetterRequest.get().getStudent();
            studentDto.setUsername(student.getUser().getUsername());
            studentDto.setFullName(student.getFullName());
            studentDto.setEmail(student.getEmail());
            studentDto.setSchool(student.getSchool());
            studentDto.setUniId(student.getUniId());
            studentDto.setUrlGradingFile(student.getUrlGradingFile());

            referenceLetterRequestDto.setId(id);
            referenceLetterRequestDto.setStudent(studentDto);
            referenceLetterRequestDto.setCarrierName(referenceLetterRequest.get().getCarrierName());
            referenceLetterRequestDto.setCarrierEmail(referenceLetterRequest.get().getCarrierEmail());

            return ResponseEntity.ok().body(referenceLetterRequestDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/pending/{id}")
    public void approveRLrequest(@PathVariable(value = "id") Integer id, String text){
        Optional<ReferenceLetterRequest> referenceLetterRequest = referenceLetterRequestRepository.findById(id);

        if (referenceLetterRequest.isPresent()) {
            ReferenceLetterRequest approvedRL = referenceLetterRequest.get();
            approvedRL.setApproved(true);
            approvedRL.setPending(false);
            approvedRL.setDeclined(false);
            referenceLetterRequestRepository.save(approvedRL);
            emailService.sendMail(approvedRL.getCarrierEmail(),"New Reference Letter",
                    "Info: " + approvedRL.toString() + "\n" +
                            "The text: " + text);
        }

    }

    @DeleteMapping("/pending/{id}")
    public void declineRLrequest(@PathVariable(value = "id") Integer id){
        Optional<ReferenceLetterRequest> referenceLetterRequest = referenceLetterRequestRepository.findById(id);

        if (referenceLetterRequest.isPresent()) {
            ReferenceLetterRequest declinedRL = referenceLetterRequest.get();
            declinedRL.setApproved(false);
            declinedRL.setPending(false);
            declinedRL.setDeclined(true);
            referenceLetterRequestRepository.save(declinedRL);
            // take student's email and inform him for declining
            emailService.sendMail(declinedRL.getStudent().getEmail(),
                    "Your reference letter request with id " + declinedRL.getId() + " is declined",
                    "Your reference letter request with id " + declinedRL.getId() + " is declined." +
                            "\nSee more: " + declinedRL.toString());
        }
    }

}
