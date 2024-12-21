package com.backend.studycenter.sc_trainerize.service.test;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.dto.test.QuestionDTO;
import com.backend.studycenter.sc_trainerize.mapper.test.QuestionMapper;
import com.backend.studycenter.sc_trainerize.model.test.Answer;
import com.backend.studycenter.sc_trainerize.model.test.Question;
import com.backend.studycenter.sc_trainerize.repository.test.QuestionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;


    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long questionId) throws EntityNotFoundException {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()) {
            throw new EntityNotFoundException("Question not found with id=" + questionId);
        }
        return questionOptional.get();
    }


    public Question save(QuestionDTO questionDTO) {
//        List<AnswerDTO> answerDTOS = questionDTO.getAnswerDTOS();
//        answerDTOS.add(new AnswerDTO(0L, "Auto added", false));
//        questionDTO.setAnswerDTOS(answerDTOS);

        Question question = questionRepository.save(QuestionMapper.INSTANCE.toModel(questionDTO));
        question.getAnswers().add(new Answer(0L, "Auto added", false));
        return question;

    }


    public Question updateById(Long id, QuestionDTO questionDTO) throws EntityNotFoundException {
        Optional<Question> questionOptional = questionRepository.findById(id);

        if (questionOptional.isEmpty()) {
            throw new EntityNotFoundException("Question not found with id=" + id);
        }
        return QuestionMapper.INSTANCE.updateQuestion(questionOptional.get(), questionDTO);
    }

    public void deleteById(Long id) throws EntityNotFoundException {
        Optional<Question> questionOptional = questionRepository.findById(id);

        if (questionOptional.isEmpty()) {
            throw new EntityNotFoundException("Question not found with id=" + id);
        }
        questionRepository.deleteById(id);
    }
}
