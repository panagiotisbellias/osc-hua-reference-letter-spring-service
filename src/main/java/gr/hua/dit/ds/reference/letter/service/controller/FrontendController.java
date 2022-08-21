package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import gr.hua.dit.ds.reference.letter.service.payload.UserInfo;
import gr.hua.dit.ds.reference.letter.service.repository.UserRepository;
import gr.hua.dit.ds.reference.letter.service.service.StudentService;
import gr.hua.dit.ds.reference.letter.service.service.TeacherService;
import gr.hua.dit.ds.reference.letter.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/")
public class FrontendController {

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;
    
    @Autowired
    TeacherService teacherService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String listUsers(Model model) {

        return "home";
    }

    @GetMapping("/addteacher") // Endpoint changed
    public String showTeacherSignUpForm(Teacher teacher) {
        return "add-teacher";
    }

    @PostMapping("/addteacher")
    public String addTeacher(@Valid Teacher teacher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-teacher";
        }

        User user = teacher.getUser();
        userService.registerUser(user, "ROLE_TEACHER");
        userService.registerTeacher(teacher);
        return "redirect:/index";
    }

    // TODO: Finish testing template for teacher

}