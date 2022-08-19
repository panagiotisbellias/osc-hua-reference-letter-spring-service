package gr.hua.dit.ds.reference.letter.service.service.impl;

import gr.hua.dit.ds.reference.letter.service.entity.Teacher;
import gr.hua.dit.ds.reference.letter.service.repository.TeacherRepository;
import gr.hua.dit.ds.reference.letter.service.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        super();
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllTeachers() {
            return teacherRepository.findAll();
        }


    @Override
    public Teacher saveTeacher(Teacher teacher) {
            //System.out.println(teacher);
            return teacherRepository.save(teacher);
        }

    @Override
    public Teacher getTeacherById(int id) {
            return teacherRepository.findById(id).get();
        }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
            return teacherRepository.save(teacher);
        }

    @Override
    public void deleteTeacherById(int id) {
        teacherRepository.deleteById(id);
        }
}
