package gr.hua.dit.ds.reference.letter.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class MainController {

    @RequestMapping("/")
    public String homePage() {
        return "main";
    }

    @RequestMapping("/admin_panel")
    public String seeUsers() {
        return "seeUsers";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

}