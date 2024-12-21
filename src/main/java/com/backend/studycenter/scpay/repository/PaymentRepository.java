package com.backend.studycenter.scpay.repository;

import com.backend.studycenter.scpay.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "from Payment p where p.student.id=:studentId and p.group.id=:groupId ")
    List<Payment> findByStudentAndGroupId(Long studentId, Long groupId);

    @Query(value = "from Payment p where p.student.id=:studentId")
    List<Payment> findAllByStudentId(Long studentId);
}
