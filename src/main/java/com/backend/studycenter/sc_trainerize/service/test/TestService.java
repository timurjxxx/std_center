package com.backend.studycenter.sc_trainerize.service.test;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.dto.test.TestDTO;
import com.backend.studycenter.sc_trainerize.mapper.test.TestMapper;
import com.backend.studycenter.sc_trainerize.model.test.Test;
import com.backend.studycenter.sc_trainerize.repository.test.TestRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    TestRepository testRepository;


    public List<Test> getAll() {
        return testRepository.findAll();
    }

    public Test getTestById(Long testId) throws EntityNotFoundException {
        Optional<Test> testOptional = testRepository.findById(testId);
        if (testOptional.isEmpty()) {
            throw new EntityNotFoundException("Test not found with id=" + testId);
        }
        return testOptional.get();
    }

    public Test save(TestDTO testDTO) {
        return testRepository.save(TestMapper.INSTANCE.toModel(testDTO));
    }

    public Test update(Long id, TestDTO testDTO) throws EntityNotFoundException {
        Optional<Test> testOptional = testRepository.findById(id);

        if (testOptional.isEmpty()) {
            throw new EntityNotFoundException("Test not found with id=" + id);
        } else {
            return TestMapper.INSTANCE.update(testOptional.get(), testDTO);
        }
    }
}
