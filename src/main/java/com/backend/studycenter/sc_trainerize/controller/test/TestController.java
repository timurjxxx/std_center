package com.backend.studycenter.sc_trainerize.controller.test;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.dto.test.TestDTO;
import com.backend.studycenter.sc_trainerize.mapper.test.TestMapper;
import com.backend.studycenter.sc_trainerize.model.test.Test;
import com.backend.studycenter.sc_trainerize.service.test.TestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/test")
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping
    public ResponseEntity<List<TestDTO>> getAll() {
        List<Test> tests = testService.getAll();
        return ResponseEntity.ok(TestMapper.mapToDTO(tests));
    }

    @GetMapping("{id}")
    public ResponseEntity<TestDTO> getTestById(@PathVariable Long id) {
        try {
            Test test = testService.getTestById(id);
            return ResponseEntity.ok(TestMapper.INSTANCE.toDTO(test));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TestDTO> addTest(@RequestBody TestDTO testDTO) {
        Test test = testService.save(testDTO);
        return ResponseEntity.ok(TestMapper.INSTANCE.toDTO(test));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestDTO> updateById(@PathVariable Long id, @RequestBody TestDTO testDTO) {
        try {
            Test test = testService.update(id, testDTO);
            return ResponseEntity.ok(TestMapper.INSTANCE.toDTO(test));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
