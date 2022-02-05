package gr.hua.dit.ds.reference.letter.service.service;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import gr.hua.dit.ds.reference.letter.service.entity.User;
import gr.hua.dit.ds.reference.letter.service.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class TeacherService implements UserDetailsService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    UserService userService;

    public void registerTeacher(User user, Teacher teacher) {

        userService.registerUser(user, "ROLE_TEACHER");

        Teacher newTeacher = new Teacher();
        newTeacher.setUser(user);
        newTeacher.setFullName(teacher.getFullName());
        newTeacher.setEmail(teacher.getEmail());
        newTeacher.setDateOfBirth(teacher.getDateOfBirth());
        newTeacher.setCertificate(teacher.getCertificates());
        newTeacher.setCourses(teacher.getCourses());
        teacherRepository.save(newTeacher);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException(String.format("Username[%s] not found", username));
    }

}
