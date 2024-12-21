package com.backend.studycenter.common.service.person;

import com.backend.studycenter.common.dto.person.TeacherDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.person.TeacherMapper;
import com.backend.studycenter.common.model.course.Course;
import com.backend.studycenter.common.model.person.Teacher;
import com.backend.studycenter.common.repository.person.TeacherRepository;
import com.backend.studycenter.common.service.course.CourseService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseService courseService;

    public ArrayList<Teacher> getTeachers() {
        return (ArrayList<Teacher>) teacherRepository.findAll();
    }

    public ArrayList<Teacher> getTeachersByCourseId(Long courseId) throws EntityNotFoundException {
        ArrayList<Teacher> teachers = new ArrayList<>();
        Course course = courseService.getCourseById(courseId);
        for (Teacher teacher : course.getTeachers()) {
            teachers.add(teacher);
        }
        return teachers;
    }

    public Teacher getTeacherById(Long teacherId) throws EntityNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Not found teacher by id = " + teacherId));
        return teacher;
    }

    public Teacher addTeacher(TeacherDTO teacherDTO) {
        return teacherRepository.save(TeacherMapper.INSTANCE.toModel(teacherDTO));
    }

    public void deleteTeacher(Long teacherId) throws EntityNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Not found teacher by id = " + teacherId));
        teacherRepository.delete(teacher);
    }

    public Teacher updateTeacher(TeacherDTO teacherDTO, Long teacherId) throws EntityNotFoundException {
        Teacher existingTeacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Not found teacher by id = " + teacherId));
        Teacher updatedTeacher = TeacherMapper.INSTANCE.toModel(teacherDTO);
        updatedTeacher.setId(existingTeacher.getId());
        return teacherRepository.save(updatedTeacher);
    }


    public void clear() {
        teacherRepository.deleteAll();
    }
}
