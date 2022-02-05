package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import gr.hua.dit.ds.reference.letter.service.service.StudentService;
import gr.hua.dit.ds.reference.letter.service.service.TeacherService;
import gr.hua.dit.ds.reference.letter.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userService.registerUser(user, null);
        return "redirect:/index";
    }

    @PostMapping("/addstudent")
    public String addStudent(@Valid Student student, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }

        studentService.registerStudent(user, student);
        return "redirect:/index";
    }

    @PostMapping("/addteacher")
    public String addTeacher(@Valid Teacher teacher, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-teacher";
        }

        teacherService.registerTeacher(user, teacher);
        return "redirect:/index";
    }

    /* Testing */

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @GetMapping("/signup/student")
    public String showStudentSignUpForm(Student student) {
        return "add-student";
    }

    @GetMapping("/signup/teacher")
    public String showTeacherSignUpForm(Teacher teacher) {
        return "add-teacher";
    }

}