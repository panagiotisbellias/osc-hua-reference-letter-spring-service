package gr.hua.dit.ds.reference.letter.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/app")
    public String homePage() {
        return "main";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

}