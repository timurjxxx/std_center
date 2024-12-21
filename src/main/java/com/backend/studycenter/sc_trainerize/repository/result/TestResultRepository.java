package com.backend.studycenter.sc_trainerize.repository.result;

import com.backend.studycenter.sc_trainerize.model.test.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
}
