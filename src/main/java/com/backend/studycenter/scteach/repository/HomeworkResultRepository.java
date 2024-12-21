package com.backend.studycenter.scteach.repository;

import com.backend.studycenter.scteach.model.HomeworkResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkResultRepository extends CrudRepository<HomeworkResult, Long> {
}
