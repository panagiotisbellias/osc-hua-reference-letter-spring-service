package gr.hua.dit.ds.reference.letter.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import gr.hua.dit.ds.reference.letter.service.entity.Teacher;
import gr.hua.dit.ds.reference.letter.service.service.TeacherService;

@Controller
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        super();
        this.teacherService = teacherService;
    }

    // handler method to handle list teachers and return mode and view
    @GetMapping("/teachers")
    public String listTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "teachers";
    }

    @GetMapping("/teacher/new")
    public String createTeacherForm(Model model) {

        // create teacher object to hold teacher form data
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "create_teacher";

    }

    @PostMapping("/teachers")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.saveTeacher(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/edit/{id}")
    public String editTeacherForm(@PathVariable int id, Model model) {
        model.addAttribute("teacher", teacherService.getTeacherById(id));
        return "edit_teacher";
    }

    @PostMapping("/teachers/{id}")
    public String updateTeacher(@PathVariable int id,
                                @ModelAttribute("teacher") Teacher teacher,
                                Model model) {

        // get teacher from database by id
        Teacher existingTeacher = teacherService.getTeacherById(id);
        existingTeacher.setId(id);
        existingTeacher.setFullName(teacher.getFullName());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setCourses(teacher.getCourses());
        existingTeacher.setCertificates(teacher.getCertificates());
        existingTeacher.setUser(teacher.getUser());

        // save updated teacher object
        teacherService.updateTeacher(existingTeacher);
        return "redirect:/teachers";
    }

    // handler method to handle delete student request

    @GetMapping("/teachers/{id}")
    public String deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacherById(id);
        return "redirect:/teachers";
    }

}
