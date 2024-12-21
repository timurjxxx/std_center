package com.backend.studycenter.common.repository.person;

import com.backend.studycenter.common.model.person.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
