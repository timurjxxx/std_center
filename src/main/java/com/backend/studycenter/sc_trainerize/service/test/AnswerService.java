package com.backend.studycenter.sc_trainerize.service.test;


import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.dto.test.AnswerDTO;
import com.backend.studycenter.sc_trainerize.mapper.test.AnswerMapper;
import com.backend.studycenter.sc_trainerize.model.test.Answer;
import com.backend.studycenter.sc_trainerize.repository.test.AnswerRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;


    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    public Answer getById(Long id) throws EntityNotFoundException {
        Optional<Answer> answerOptional = answerRepository.findById(id);

        if (answerOptional.isEmpty()) {
            throw new EntityNotFoundException("Answer not found with id=" + id);
        } else {
            return answerOptional.get();
        }
    }


    public Answer save(AnswerDTO answerDTO) {
        return answerRepository.save(AnswerMapper.INSTANCE.toModel(answerDTO));
    }

    public List<Answer> saveAll(List<AnswerDTO> answerDTOS) {
        List<Answer> answers = new ArrayList<>();
        for (AnswerDTO answerDTO : answerDTOS) {
            Answer answer = AnswerMapper.INSTANCE.toModel(answerDTO);
            answers.add(answerRepository.save(answer));
        }
        return answers;
    }

    public Answer updateById(Long answerId, AnswerDTO answerDTO) throws EntityNotFoundException {
        Optional<Answer> answerOptional = answerRepository.findById(answerId);

        if (answerOptional.isEmpty()) {
            throw new EntityNotFoundException("Answer not found with id=" + answerId);
        } else {
            return AnswerMapper.INSTANCE.updateAnswer(answerOptional.get(), answerDTO);
        }

    }

    public void delete(Long id) throws EntityNotFoundException {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isEmpty()) {
            throw new EntityNotFoundException("Answer not found with id=" + id);
        }
        answerRepository.deleteById(id);
    }
}
