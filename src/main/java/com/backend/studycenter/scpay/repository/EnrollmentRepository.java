package com.backend.studycenter.scpay.repository;

import com.backend.studycenter.scpay.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
