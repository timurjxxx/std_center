package com.backend.studycenter.scpay.repository;

import java.util.List;
import java.util.Optional;


import com.backend.studycenter.scpay.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    @Query(value = "from Contract c where c.student.id=:sId and c.group.id=:gId")
    Optional<Contract> findByStudentIdAndGroupId(@Param("sId") Long studentId, @Param("gId") Long groupId);

    @Query(value = "from Contract  c where c.student.id=:studentId")
    List<Contract> finAllByStudentId(Long studentId);

    @Query(value = "from Contract c where c.teacher.id=:teacherId")
    Optional<Contract> findByTeacherId(Long teacherId);

}
