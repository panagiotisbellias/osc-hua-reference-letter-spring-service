package gr.hua.dit.ds.reference.letter.service.controller;

import gr.hua.dit.ds.reference.letter.service.entity.Teacher;
import gr.hua.dit.ds.reference.letter.service.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/")
    public List<Teacher> retrieveAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public Teacher retrieveTeacher(@PathVariable int id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);

        if (teacher.isEmpty())
            throw new TeacherNotFoundException("id-" + id);

        return teacher.get();
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable int id) {
        teacherRepository.deleteById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherRepository.save(teacher);
        System.out.println("teacher id " + savedTeacher.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTeacher.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTeacher(@RequestBody Teacher teacher, @PathVariable int id) {

        Optional<Teacher> teacherOptional = teacherRepository.findById(id);

        if (teacherOptional.isEmpty())
            return ResponseEntity.notFound().build();

        teacher.setId(id);

        teacherRepository.save(teacher);

        return ResponseEntity.noContent().build();

    }

}
