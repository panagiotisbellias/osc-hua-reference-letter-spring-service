package gr.hua.dit.ds.reference.letter.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping(method= RequestMethod.GET, value={"/","/home"})
    public String homePage(){
        return "";
    }
}
