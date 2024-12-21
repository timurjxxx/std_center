package com.backend.studycenter.scteach.repository;

import com.backend.studycenter.scteach.model.Respond;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespondRepository extends CrudRepository<Respond, Long> {
}
