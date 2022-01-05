package gr.hua.dit.ds.reference.letter.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.ds.reference.letter.service.entity.ReferenceLetterRequest;
import gr.hua.dit.ds.reference.letter.service.repository.ReferenceLetterRequestRepository;

import java.util.List;

public class ReferenceLetterRequestController {

    @Autowired
    private ReferenceLetterRequestRepository referenceLetterRequestRepository;

    @RequestMapping("/new_application")
    public String newApplication() {
        return "newApplication";
    }

    @GetMapping("/my_applications")
    public String myApplications(Model model) {
        List<ReferenceLetterRequest> referenceLetterRequests = referenceLetterRequestRepository.findAll();
        model.addAttribute("referenceLetterRequests", referenceLetterRequests);
        return "myApplications";
    }

}
