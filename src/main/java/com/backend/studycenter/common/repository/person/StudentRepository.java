package com.backend.studycenter.common.repository.person;

import com.backend.studycenter.common.model.person.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
