package com.backend.studycenter.sc_trainerize.controller.test;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.dto.test.ExamDTO;
import com.backend.studycenter.sc_trainerize.mapper.test.ExamMapper;
import com.backend.studycenter.sc_trainerize.model.test.Exam;
import com.backend.studycenter.sc_trainerize.service.test.ExamService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/exam")
public class ExamController {

    @Autowired
    ExamService examService;

    @GetMapping
    public ResponseEntity<List<ExamDTO>> getExams() {
        List<Exam> exams = examService.getAll();
        List<ExamDTO> examDTOS = new ArrayList<>();
        for (Exam exam : exams) {
            examDTOS.add(ExamMapper.INSTANCE.toDTO(exam));
        }
        if (examDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.ok(examDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamDTO> getExamById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        ExamDTO examDTO = ExamMapper.INSTANCE.toDTO(examService.getExamById(id));
        if (examDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(examDTO);
        }
    }

    @PostMapping
    public ResponseEntity<ExamDTO> addExam(@RequestBody ExamDTO examDTO) {
        try {
            return ResponseEntity.ok(ExamMapper.INSTANCE.toDTO(examService.addExam(examDTO)));
        } catch (Exception e) {
            System.out.println("Problem is happened");
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) throws EntityNotFoundException {
        try {
            examService.deleteExam(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println("Problem is happened");
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamDTO> updateExam(@RequestBody ExamDTO examDTO, @PathVariable Long id) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(ExamMapper.INSTANCE.toDTO(examService.updateExam(examDTO, id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println("Problem is happened");
            return ResponseEntity.internalServerError().build();
        }
    }
}
