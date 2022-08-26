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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/edit/{username}")
    public String editUserForm(@PathVariable String username, Model model) {
        model.addAttribute("user", userRepository.findByUsername(username).get());
        return "edit_user";
    }

    @PostMapping("/users/{username}")
    public String updateUser(@PathVariable String username,
                                @ModelAttribute("user") User updatedUser,
                                Model model) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user == null) return "redirect:/admin/";
        User existingUser = user.get();
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());

        // save updated teacher object
        userService.updateUser(existingUser);
        return "redirect:/admin/";
    }

    @GetMapping("/users/{username}")
    public String deleteUser(@PathVariable String username) {
        userService.deleteUser(userRepository.findByUsername(username).get());
        return "redirect:/admin/";
    }

}