package com.backend.studycenter.common.repository.education;

import com.backend.studycenter.common.model.education.CalendarLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarLessonRepository extends JpaRepository<CalendarLesson, Long> {
}
