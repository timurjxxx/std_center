package com.backend.studycenter.scpay.repository;

import com.backend.studycenter.scpay.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    @Query(value = "from Program p where p.name=:name")
    Optional<Program> findByName(String name);

    @Query(value = "from Program p where p.isActive=true ")
    List<Program> findAllActive();
}
