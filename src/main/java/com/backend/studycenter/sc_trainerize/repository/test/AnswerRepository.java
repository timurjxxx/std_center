package com.backend.studycenter.sc_trainerize.repository.test;

import com.backend.studycenter.sc_trainerize.model.test.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
