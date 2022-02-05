package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RepositoryEventHandler
public class TeacherEventHandler {
    @Autowired
    UserEventHandler userEventHandler;

    @Autowired
    private TeacherRepository teacherRepository;

    @HandleBeforeCreate
    public void handleTeacherCreate(User user, Teacher teacher) {

        userEventHandler.handleUserCreate(user, "ROLE_TEACHER");
        teacherRepository.save(teacher);
    }

    @HandleBeforeSave
    public void handleTeacherUpdate(User user, Teacher teacher) {
        userEventHandler.handleUserUpdate(user);

        List<Course> courses = teacher.getCourses();
        if (courses == null) {
            //keeps the last courses
            Optional<Teacher> storedTeacher = teacherRepository.findById(teacher.getId());
            storedTeacher.ifPresent(value -> teacher.setCourses(value.getCourses()));
        } else {
            //add courses
            for (Course course : courses) {
                teacher.addCourse(course);
            }
        }

        List<Certificate> certificates = teacher.getCertificates();
        if (certificates == null) {
            //keeps the last courses
            Optional<Teacher> storedTeacher = teacherRepository.findById(teacher.getId());
            storedTeacher.ifPresent(value -> teacher.setCertificate(value.getCertificates()));
        } else {
            //add courses
            for (Certificate certificate : certificates) {
                teacher.addCertificate(certificate);
            }
        }

    }

}
