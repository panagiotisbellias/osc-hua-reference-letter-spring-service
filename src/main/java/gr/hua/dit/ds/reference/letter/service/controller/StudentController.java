package gr.hua.dit.ds.reference.letter.service.controller;

import java.util.*;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import gr.hua.dit.ds.reference.letter.service.entity.Student;
import gr.hua.dit.ds.reference.letter.service.repository.StudentRepository;

@RestController
@RequestMapping("/students")
@Secured({"ROLE_ADMIN", "ROLE_STUDENT"})
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public List<Student> retrieveAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student retrieveStudent(@PathVariable int id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty())
            throw new StudentNotFoundException("id-" + id);

        return student.get();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentRepository.deleteById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        System.out.println("student id " + savedStudent.getId());


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable int id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isEmpty())
            return ResponseEntity.notFound().build();

        student.setId(id);

        studentRepository.save(student);

        return ResponseEntity.noContent().build();
    }


}