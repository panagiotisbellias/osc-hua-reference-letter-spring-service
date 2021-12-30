package project.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class StudentController {

    @RequestMapping("/new_application")
    public String newApplication() {
        return "newApplication";
    }

    @RequestMapping("/my_applications")
    public String myApplications() {
        return "myApplications";
    }

}
