package gr.hua.dit.ds.reference.letter.service.repository;

import gr.hua.dit.ds.reference.letter.service.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class StudentEventHandler {

    @Autowired
    UserEventHandler userEventHandler;

    @Autowired
    private StudentRepository studentRepository;

    @HandleBeforeCreate
    public void handleStudentCreate(User user, Student student) {

        userEventHandler.handleUserCreate(user, "ROLE_STUDENT");
        studentRepository.save(student);
    }

    @HandleBeforeSave
    public void handleStudentUpdate(User user) {
        userEventHandler.handleUserUpdate(user);
    }

}
