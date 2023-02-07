package gr.hua.dit.ds.reference.letter.service.service;

import java.util.List;

import gr.hua.dit.ds.reference.letter.service.entity.Student;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudentById(int id);

    Student getStudentByUsername(String username);

    Student updateStudent(Student student);

    void deleteStudentById(int id);
}
