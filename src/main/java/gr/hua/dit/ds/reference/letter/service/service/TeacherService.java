package gr.hua.dit.ds.reference.letter.service.service;

import gr.hua.dit.ds.reference.letter.service.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();

    Teacher saveTeacher(Teacher student);

    Teacher getTeacherById(int id);

    Teacher updateTeacher(Teacher student);

    void deleteTeacherById(int id);
}
