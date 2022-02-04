package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import gr.hua.dit.ds.reference.letter.service.repository.*;
// import gr.hua.dit.ds.reference.letter.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @PostMapping("/signup/student")
    public String /*ResponseEntity<Object>*/ signUpStudent(@RequestBody Map<String, Object> user, @RequestBody Student student) {

        /*
        Student savedStudent = studentRepository.save(student);
        System.out.println("student id " + savedStudent.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();
        */
        System.out.println(user);
        System.out.println(student);

        return "DONE";
    }

    /*
    @PostMapping("/signup")
    public String signUp(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setEnabled(true);
        userRepository.save(newUser);

        if(user.getEntity().equals("Student")){
            Student newStudent = new Student();
            studentRepository.save(newStudent);
            Authorities auth = new Authorities("ROLE_STUDENT", newUser);
            authRepository.save(auth);
        }
        else if (user.getEntity().equals("Teacher")){
            Teacher newTeacher = new Teacher();
            teacherRepository.save(newTeacher);
            Authorities auth = new Authorities("ROLE_TEACHER", newUser);
            authRepository.save(auth);
        }



        return "redirect:/index";
    }

    @RequestMapping("/profile")
    public String profile() {
        return "";
    }
        */
}