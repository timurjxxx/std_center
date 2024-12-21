package com.backend.studycenter.common.service.person;

import com.backend.studycenter.common.dto.person.StudentDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.person.StudentMapper;
import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.common.repository.person.StudentRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public ArrayList<Student> getStudents() {
        return (ArrayList<Student>) studentRepository.findAll();
    }

    public Student getStudentById(Long studentId) throws EntityNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Not found student by id = " + studentId));
        return student;
    }

    public Student addStudent(StudentDTO studentDTO) {
        return studentRepository.save(StudentMapper.INSTANCE.toModel(studentDTO));
    }

    public void deleteStudent(Long studentId) throws EntityNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Not found student by id = " + studentId));
        studentRepository.delete(student);
    }

    public Student updateStudent(StudentDTO studentDTO, Long studentId) throws EntityNotFoundException {
        Student existingStudent = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Not found student by id = " + studentId));
        Student updatedStudent = StudentMapper.INSTANCE.toModel(studentDTO);
        updatedStudent.setId(existingStudent.getId());
        return studentRepository.save(updatedStudent);
    }

    public void clear() {
        studentRepository.deleteAll();
    }
}

