package gr.hua.dit.ds.reference.letter.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping(method= RequestMethod.GET, value={"/","/home"})
    public String homePage(@RequestParam(name="name", required = false, defaultValue = "") String name, Model model){
        model.addAttribute("name", name);
        return "home";
    }
}
