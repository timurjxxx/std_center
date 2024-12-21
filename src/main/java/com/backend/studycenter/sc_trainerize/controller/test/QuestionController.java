package com.backend.studycenter.sc_trainerize.controller.test;


import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.dto.test.QuestionDTO;
import com.backend.studycenter.sc_trainerize.mapper.test.QuestionMapper;
import com.backend.studycenter.sc_trainerize.model.test.Question;
import com.backend.studycenter.sc_trainerize.service.test.QuestionService;
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
@RequestMapping(value = "api/v1/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;


    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        List<Question> questions = questionService.getAll();

        if (questions.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<QuestionDTO> questionDTOS = QuestionMapper.mapToDTO(questions);
            return ResponseEntity.ok(questionDTOS);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getById(@PathVariable Long id) {
        try {
            Question question = questionService.getQuestionById(id);
            return ResponseEntity.ok(QuestionMapper.INSTANCE.toDTO(question));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<QuestionDTO> saveQuestion(@RequestBody QuestionDTO questionDTO) {
        Question question = questionService.save(questionDTO);
        return ResponseEntity.ok(QuestionMapper.INSTANCE.toDTO(question));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionDTO> updateById(@PathVariable Long id, @RequestBody QuestionDTO questionDTO) {
        try {
            Question question = questionService.updateById(id, questionDTO);
            return ResponseEntity.ok(QuestionMapper.INSTANCE.toDTO(question));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            questionService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
