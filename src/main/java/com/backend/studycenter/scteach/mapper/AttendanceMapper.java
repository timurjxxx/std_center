package com.backend.studycenter.scteach.mapper;

import com.backend.studycenter.scteach.dto.AttendanceDTO;
import com.backend.studycenter.scteach.model.Attendance;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AttendanceMapper {
    AttendanceMapper INSTANCE = Mappers.getMapper(AttendanceMapper.class);

    //    @Mapping(source = "attendance", target = "attendanceDTO")
    Attendance toModel(AttendanceDTO attendanceDTO);

    //    @Mapping(source = "attendanceDTO", target = "attendance")
    AttendanceDTO toDTO(Attendance attendance);

    Attendance toModel(@MappingTarget Attendance attendance, AttendanceDTO attendanceDTO);

    static List<AttendanceDTO> toDTOs(@NotNull List<Attendance> attendance) {
        return attendance.stream().map((attendances) ->
                new AttendanceDTO(attendances.getId(), attendances.getGroupID(),
                        attendances.getCalendarLessonID())).toList();
    }
}
