package project.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class TeacherController {

    @RequestMapping("/see_pending")
    public String seePending() {
        return "seePending";
    }

}