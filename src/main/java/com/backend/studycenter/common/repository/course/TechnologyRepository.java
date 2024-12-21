package com.backend.studycenter.common.repository.course;

import com.backend.studycenter.common.model.course.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
