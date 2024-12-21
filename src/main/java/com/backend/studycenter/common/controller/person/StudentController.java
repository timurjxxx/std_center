package com.backend.studycenter.common.controller.person;

import com.backend.studycenter.common.dto.person.StudentDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.person.StudentMapper;
import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.common.service.person.StudentService;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private StudentService studentService;

    
    @GetMapping(value = "/api/v1/students")
    public ResponseEntity<ArrayList<StudentDTO>> getStudents() {
        ArrayList<Student> students = studentService.getStudents();
        ArrayList<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            studentDTOs.add(StudentMapper.INSTANCE.toDTO(student));
        }
        if (studentDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(studentDTOs);
        }
    }

    
    @GetMapping(value = "/api/v1/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable(name = "id") Long studentId) throws EntityNotFoundException {
        StudentDTO studentDTO = StudentMapper.INSTANCE.toDTO(studentService.getStudentById(studentId));
        if (studentDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(studentDTO);
        }
    }

    
    @PostMapping(value = "/api/v1/students", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        try {
            return ResponseEntity.ok(StudentMapper.INSTANCE.toDTO(studentService.addStudent(studentDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PutMapping(value = "/api/v1/students/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable(name = "id") Long studentId) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(StudentMapper.INSTANCE.toDTO(studentService.updateStudent(studentDTO, studentId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @DeleteMapping(value = "/api/v1/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable(name = "id") Long studentId) throws EntityNotFoundException {
        try {
            studentService.deleteStudent(studentId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
