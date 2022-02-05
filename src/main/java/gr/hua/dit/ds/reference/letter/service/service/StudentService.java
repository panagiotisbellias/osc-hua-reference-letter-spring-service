package gr.hua.dit.ds.reference.letter.service.service;

import gr.hua.dit.ds.reference.letter.service.entity.Student;
import gr.hua.dit.ds.reference.letter.service.entity.User;
import gr.hua.dit.ds.reference.letter.service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    UserService userService;

    public void registerStudent(User user, Student student) {

        userService.registerUser(user, "ROLE_STUDENT");

        Student newStudent = new Student();
        newStudent.setUser(user);
        newStudent.setFullName(student.getFullName());
        newStudent.setEmail(student.getEmail());
        newStudent.setSchool(student.getSchool());
        newStudent.setUniId(student.getUniId());
        newStudent.setUrlGradingFile(student.getUrlGradingFile());
        newStudent.setDateOfBirth(student.getDateOfBirth());
        studentRepository.save(newStudent);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
    }

}
