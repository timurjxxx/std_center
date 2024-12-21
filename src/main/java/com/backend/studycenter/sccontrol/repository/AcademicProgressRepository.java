package com.backend.studycenter.sccontrol.repository;

import com.backend.studycenter.common.model.course.Course;
import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.sccontrol.model.AcademicProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcademicProgressRepository  extends JpaRepository<AcademicProgress,Long> {
    AcademicProgress findByCourseAndStudent(Course course, Student student);
}
