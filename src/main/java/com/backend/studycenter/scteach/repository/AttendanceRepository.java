package com.backend.studycenter.scteach.repository;

import com.backend.studycenter.scteach.model.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
}
