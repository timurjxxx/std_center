package com.backend.studycenter.sccontrol.repository;

import com.backend.studycenter.sccontrol.model.InterviewResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewResultRepository extends JpaRepository<InterviewResult, Long> {

    InterviewResult findInterviewResultById(Long id);


}
