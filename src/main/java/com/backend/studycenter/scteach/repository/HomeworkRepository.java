package com.backend.studycenter.scteach.repository;

import com.backend.studycenter.scteach.model.Homework;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepository extends CrudRepository<Homework, Long> {
}
