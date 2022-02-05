package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.Student;
import gr.hua.dit.ds.reference.letter.service.entity.User;
import gr.hua.dit.ds.reference.letter.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userService.registerUser(user);
        return "redirect:/index";
    }

    @GetMapping("/signup/student")
    public String showStudentSignUpForm(User user) {
        return "add-student";
    }

    @PostMapping("/addstudent")
    public String addStudent(@@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userService.registerStudent(student);
        return "redirect:/index";
    }

}