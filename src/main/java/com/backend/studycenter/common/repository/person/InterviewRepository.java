package com.backend.studycenter.common.repository.person;

import com.backend.studycenter.common.model.person.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
}
