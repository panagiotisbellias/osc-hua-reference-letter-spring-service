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
@RequestMapping("/admin")
public class UserController {

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
        System.out.println("DEBUG!");

        List<User> users = userService.getAllUsers();
        List<UserInfo> userDetails = new ArrayList<>();
        for (User u : users) {
            Optional<User> opt_user = userRepository.findByUsername(u.getUsername());
            if (opt_user == null) break;
            User user = opt_user.get();
            UserInfo userInfo = new UserInfo();
                Student student = studentService.getStudentByUsername(user.getUsername());
                if (student != null) {
                    userInfo = new UserInfo(user.getUsername(), student.getFullName(), student.getEmail(), "Student");
                }
                else {
                    Teacher teacher = teacherService.getTeacherByUsername(user.getUsername());
                    if (teacher != null) {
                        userInfo = new UserInfo(user.getUsername(), teacher.getFullName(), teacher.getEmail(), "Teacher");
                    } else continue;
                }
            userDetails.add(userInfo);
        }
        model.addAttribute("userDetails", userDetails);
        return "admin-home";
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