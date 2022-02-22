package gr.hua.dit.ds.reference.letter.service.service;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import gr.hua.dit.ds.reference.letter.service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    private TeacherService teacherService;

    public void registerUser(User user, String authority) {

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEnabled(1); // enabled

        Authorities auth = new Authorities(authority, newUser);
        newUser.addAuthority(auth);

        userRepository.save(newUser);
        authRepository.save(auth);

    }

    public void deleteUser(User user) {

        authRepository.deleteAll(user.getAuthorities());
        userRepository.delete(user);

    }

    public void registerStudent(Student student) {

        Student newStudent = new Student();
        newStudent.setFullName(student.getFullName());
        newStudent.setEmail(student.getEmail());
        newStudent.setSchool(student.getSchool());
        newStudent.setUniId(student.getUniId());
        newStudent.setUrlGradingFile(student.getUrlGradingFile());
        newStudent.setUser(userRepository.findByUsername(student.getUser().getUsername()).get());

        studentRepository.save(newStudent);

    }

    public void registerTeacher(Teacher teacher) {

        Teacher newTeacher = new Teacher();
        newTeacher.setFullName(teacher.getFullName());
        newTeacher.setEmail(teacher.getEmail());
        //newTeacher.setCourses(teacher.getCourses());
        //newTeacher.setCertificates(teacher.getCertificates());

        newTeacher.setUser(userRepository.findByUsername(teacher.getUser().getUsername()).get());
        teacherRepository.save(newTeacher);
        //courseRepository.saveAll(newTeacher.getCourses());
        //certificateRepository.saveAll(newTeacher.getCertificates());

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),  user.get().getAuthorities());
        }else{
            throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
        }

    }

}
