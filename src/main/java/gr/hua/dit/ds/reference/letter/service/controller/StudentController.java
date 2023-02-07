package gr.hua.dit.ds.reference.letter.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import gr.hua.dit.ds.reference.letter.service.service.ReferenceLetterService;
import gr.hua.dit.ds.reference.letter.service.entity.ReferenceLetterRequest;
import gr.hua.dit.ds.reference.letter.service.entity.Student;
import gr.hua.dit.ds.reference.letter.service.service.StudentService;

@Controller
@RequestMapping("/admin")
public class StudentController {

    private StudentService studentService;
    private ReferenceLetterService referenceLetterService;

    public StudentController(StudentService studentService, ReferenceLetterService referenceLetterService) {
        super();
        this.studentService = studentService;
        this.referenceLetterService = referenceLetterService;
    }

    // handler method to handle list students and return mode and view
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {

        // create student object to hold student form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";

    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable int id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable int id,
                                @ModelAttribute("student") Student student,
                                Model model) {

        // get student from database by id
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFullName(student.getFullName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setSchool(student.getSchool());
        existingStudent.setUniId(student.getUniId());
        existingStudent.setUrlGradingFile(student.getUrlGradingFile());
        existingStudent.setUser(student.getUser());

        // save updated student object
        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    // handler method to handle delete student request

    @GetMapping("/students/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    // handler method to handle list rl requests and return mode and view
    @GetMapping("/rl_requests")
    public String listRLrequests(Model model) {
        model.addAttribute("rl_requests", referenceLetterService.getAllRLrequests());
        return "rl_requests";
    }

    @GetMapping("/rl_requests/new")
    public String createRLrequestForm(Model model) {

        // create student object to hold student form data
        ReferenceLetterRequest referenceLetterRequest = new ReferenceLetterRequest();
        model.addAttribute("rl_request", referenceLetterRequest);
        return "create_rl_request";

    }

    @PostMapping("/rl_requests")
    public String saveRLrequest(@ModelAttribute("rl_request") ReferenceLetterRequest referenceLetterRequest) {
        referenceLetterService.saveRLrequest(referenceLetterRequest);
        return "redirect:/rl_requests";
    }

    @GetMapping("/rl_requests/edit/{id}")
    public String editRLrequestForm(@PathVariable int id, Model model) {
        model.addAttribute("rl_request", referenceLetterService.getRLrequestById(id));
        return "edit_rl_request";
    }

    @PostMapping("/rl_requests/{id}")
    public String updateRLrequest(@PathVariable int id,
                                @ModelAttribute("rl_request") ReferenceLetterRequest referenceLetterRequest,
                                Model model) {

        // get rl request from database by id
        ReferenceLetterRequest existingRLrequest = referenceLetterService.getRLrequestById(id);
        existingRLrequest.setId(id); /*
        existingRLrequest.setFullName(referenceLetterRequest.getFullName());
        existingRLrequest.setEmail(referenceLetterRequest.getEmail());
        existingRLrequest.setSchool(referenceLetterRequest.getSchool());
        existingRLrequest.setUniId(referenceLetterRequest.getUniId());
        existingRLrequest.setUrlGradingFile(referenceLetterRequest.getUrlGradingFile());
        existingRLrequest.setUser(referenceLetterRequest.getUser()); */

        // save updated student object
        referenceLetterService.updateRLrequest(existingRLrequest);
        return "redirect:/admin/rl_requests/";
    }

    // handler method to handle delete student request

    @DeleteMapping("/rl_requests/{id}")
    public String deleteRLrequest(@PathVariable int id) {
        referenceLetterService.deleteRLrequestById(id);
        return "redirect:/rl_requests";
    }

}
