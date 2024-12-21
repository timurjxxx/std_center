package com.backend.studycenter.scteach.service;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.scteach.dto.AttendanceDTO;
import com.backend.studycenter.scteach.mapper.AttendanceMapper;
import com.backend.studycenter.scteach.model.Attendance;
import com.backend.studycenter.scteach.repository.AttendanceRepository;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Attendance> getAttendances() {
        return (List<Attendance>) attendanceRepository.findAll();
    }

    public Attendance getAttendanceById(@NotNull Long id) throws EntityNotFoundException {
        return attendanceRepository.findById(id).orElseThrow(() -> new
                EntityNotFoundException("Not found attendance with id = \"" + id));
    }

    public Attendance addAttendance(@NotNull AttendanceDTO attendanceDTO) {
        return attendanceRepository.save(AttendanceMapper.INSTANCE.toModel(attendanceDTO));
    }

    public Attendance deleteAttendanceById(@NotNull Long id) throws EntityNotFoundException {
        Attendance attendance = attendanceRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found attendance with id = \"" + id));
        attendanceRepository.delete(attendance);
        return attendance;
    }

    public Attendance update(@NotNull Long attendanceId, AttendanceDTO attendanceDTO) throws EntityNotFoundException {
        return AttendanceMapper.INSTANCE.toModel(attendanceRepository.
                findById(attendanceId).orElseThrow(() ->
                        new EntityNotFoundException("Not found attendance with id = \"" + attendanceId)), attendanceDTO);
    }

    public void clear() {
        attendanceRepository.deleteAll();
    }
}
