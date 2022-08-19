package gr.hua.dit.ds.reference.letter.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RedirectController {

    @GetMapping("/")
    public String redirect2AdminHome(Model model) {
        return "redirect:/admin/";
    }
}