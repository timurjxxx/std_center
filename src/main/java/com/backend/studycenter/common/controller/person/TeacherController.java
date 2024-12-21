package com.backend.studycenter.common.controller.person;

import com.backend.studycenter.common.dto.person.TeacherDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.person.TeacherMapper;
import com.backend.studycenter.common.messaging.EmailService;
import com.backend.studycenter.common.model.person.Teacher;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.common.service.person.TeacherService;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class TeacherController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);

    @Autowired
    private EmailService emailService;

    @Autowired
    private TeacherService teacherService;

    @Value("${email.subject.name}")
    private String emailSubject;

    @Value("${email.subject.text}")
    private String emailText;

    
    @GetMapping(value = "/api/v1/teachers")
    public ResponseEntity<ArrayList<TeacherDTO>> getTeachers() {


        ArrayList<Teacher> teachers = teacherService.getTeachers();
        ArrayList<TeacherDTO> teacherDTOs = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacherDTOs.add(TeacherMapper.INSTANCE.toDTO(teacher));
        }
        if (teacherDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(teacherDTOs);
        }
    }

    
    @GetMapping(value = "/api/v1/teachers/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable(name = "id") Long teacherId) throws EntityNotFoundException {
        TeacherDTO teacherDTO = TeacherMapper.INSTANCE.toDTO(teacherService.getTeacherById(teacherId));
        if (teacherDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(teacherDTO);
        }
    }

    
    @PostMapping(value = "/api/v1/teachers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDTO> addTeacher(@RequestBody TeacherDTO teacherDTO) {
        try {
            return ResponseEntity.ok(TeacherMapper.INSTANCE.toDTO(teacherService.addTeacher(teacherDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PutMapping(value = "/api/v1/teachers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO, @PathVariable(name = "id") Long teacherId) throws EntityNotFoundException {


        try {
           Teacher teacher = teacherService.updateTeacher(teacherDTO, teacherId);
//           emailService.sendEmail(teacher.getEmail(), emailSubject, emailText);

            return ResponseEntity.ok(TeacherMapper.INSTANCE.toDTO(teacher));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @DeleteMapping(value = "/api/v1/teachers/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable(name = "id") Long teacherId) throws EntityNotFoundException {
        try {
            teacherService.deleteTeacher(teacherId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
