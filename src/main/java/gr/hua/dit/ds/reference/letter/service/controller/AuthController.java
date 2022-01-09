package gr.hua.dit.ds.reference.letter.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @RequestMapping("/signup/student")
    public String signUpStudent() {
        return "";
    }

    @RequestMapping("/signup/teacher")
    public String signUpTeacher() {
        return "";
    }

    @RequestMapping("/profile")
    public String profile() {
        return "";
    }

}