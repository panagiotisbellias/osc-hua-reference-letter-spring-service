package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.Authorities;
import gr.hua.dit.ds.reference.letter.service.entity.Student;
import gr.hua.dit.ds.reference.letter.service.entity.Teacher;
import gr.hua.dit.ds.reference.letter.service.entity.User;
import gr.hua.dit.ds.reference.letter.service.repository.AuthRepository;
import gr.hua.dit.ds.reference.letter.service.repository.StudentRepository;
import gr.hua.dit.ds.reference.letter.service.repository.TeacherRepository;
import gr.hua.dit.ds.reference.letter.service.repository.UserRepository;
import gr.hua.dit.ds.reference.letter.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;



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

}