package gr.hua.dit.ds.reference.letter.service.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/admin_panel")
    @Secured("ROLE_ADMIN")
    public String seeUsers() {
        return "seeUsers";
    }

}