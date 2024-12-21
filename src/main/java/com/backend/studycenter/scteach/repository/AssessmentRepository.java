package com.backend.studycenter.scteach.repository;

import com.backend.studycenter.scteach.model.Assessment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentRepository extends CrudRepository<Assessment, Long> {
}
