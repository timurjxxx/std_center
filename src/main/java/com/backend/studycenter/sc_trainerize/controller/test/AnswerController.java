package com.backend.studycenter.sc_trainerize.controller.test;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.dto.test.AnswerDTO;
import com.backend.studycenter.sc_trainerize.mapper.test.AnswerMapper;
import com.backend.studycenter.sc_trainerize.model.test.Answer;
import com.backend.studycenter.sc_trainerize.service.test.AnswerService;
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
@RequestMapping("api/v1/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @GetMapping
    public ResponseEntity<List<AnswerDTO>> getAll() {
        List<Answer> answers = answerService.getAll();
        if (answers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            List<AnswerDTO> answerDTOS = AnswerMapper.mapToDTO(answers);
            return ResponseEntity.ok(answerDTOS);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerDTO> getAnswersById(@PathVariable Long id) {
        try {
            Answer answer = answerService.getById(id);
            AnswerDTO answerDTOS = AnswerMapper.INSTANCE.toDTO(answer);
            return ResponseEntity.ok(answerDTOS);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AnswerDTO> addAnswer(@RequestBody AnswerDTO answerDTO) {
        Answer answer = answerService.save(answerDTO);
        return ResponseEntity.ok(AnswerMapper.INSTANCE.toDTO(answer));
    }

    @PutMapping("/{answerId}")
    public ResponseEntity<AnswerDTO> updateAnswerById(@PathVariable(name = "answerId") Long answerId, @RequestBody AnswerDTO answerDTO) {
        Answer answer;
        try {
            answer = answerService.updateById(answerId, answerDTO);
            return ResponseEntity.ok(AnswerMapper.INSTANCE.toDTO(answer));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            answerService.delete(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
