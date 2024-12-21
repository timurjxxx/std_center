package com.backend.studycenter.sc_trainerize.service.test;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.dto.test.ExamDTO;
import com.backend.studycenter.sc_trainerize.mapper.test.ExamMapper;
import com.backend.studycenter.sc_trainerize.model.test.Exam;
import com.backend.studycenter.sc_trainerize.repository.test.ExamRepository;
import com.backend.studycenter.sc_trainerize.repository.topic.ModuleRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    ModuleRepository moduleRepository;

    public List<Exam> getAll() {
        return examRepository.findAll();
    }

    public Exam getExamById(Long id) throws EntityNotFoundException {
        Optional<Exam> optionalExam = examRepository.findById(id);
        if (optionalExam.isEmpty()) {
            throw new EntityNotFoundException("Exam not found with id=" + id);
        }
        return optionalExam.get();
    }

    public Exam addExam(ExamDTO examDTO) {
        return examRepository.save(ExamMapper.INSTANCE.toModel(examDTO));
    }

    public void deleteExam(Long id) throws EntityNotFoundException {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found exam by id" + id));
        examRepository.deleteById(id);
    }

    public Exam updateExam(ExamDTO examDTO, Long id) throws EntityNotFoundException {
        Exam existingExam = examRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found exam id=" + id));
        Exam updateExam = ExamMapper.INSTANCE.toModel(examDTO);
        updateExam.setId(existingExam.getId());
        return examRepository.save(updateExam);
    }

}
