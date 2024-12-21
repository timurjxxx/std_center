package com.backend.studycenter.scpay.repository;

import java.util.List;
import java.util.Optional;

import com.backend.studycenter.scpay.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query(value = "from Department d where d.name=:name")
    Optional<Department> findByName(String name);

    @Query(value = "from Department d where d.isActive=true ")
    List<Department> findAllIsActive();
}
