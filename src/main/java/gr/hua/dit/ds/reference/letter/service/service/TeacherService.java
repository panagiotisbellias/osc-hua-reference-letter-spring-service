package gr.hua.dit.ds.reference.letter.service.service;

import gr.hua.dit.ds.reference.letter.service.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();

    Teacher saveTeacher(Teacher teacher);

    Teacher getTeacherById(int id);

    Teacher getTeacherByUsername(String username);

    Teacher updateTeacher(Teacher teacher);

    void deleteTeacherById(int id);
}
