package com.backend.studycenter.scpay.repository;

import java.util.List;

import com.backend.studycenter.scpay.model.Subcontract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubcontractRepository extends JpaRepository<Subcontract, Long> {
    @Query(value = "from Subcontract s where s.contract.id=:cId")
    List<Subcontract> findAllByContractId(@Param("cId") Long contractId);

    @Query(value = "from Subcontract s where s.contract.student.id=:studentId")
    List<Subcontract> findAllByStudentId(Long studentId);

    @Query(value = "from Subcontract s where s.contract.student.id=:studentId and s.contract.group.id=:groupId")
    List<Subcontract> findAllBystudentGroupId(Long studentId, Long groupId);
}
