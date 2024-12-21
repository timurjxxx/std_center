package com.backend.studycenter.common.mapper.education;

import com.backend.studycenter.common.dto.education.CalendarLessonDTO;
import com.backend.studycenter.common.model.education.CalendarLesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CalendarLessonMapper {
    CalendarLessonMapper INSTANCE = Mappers.getMapper(CalendarLessonMapper.class);

    @Mapping(source = "lesson", target = "lessonDTO")
    CalendarLessonDTO toDTO(CalendarLesson calendarLesson);

    @Mapping(source = "lessonDTO", target = "lesson")
    CalendarLesson toModel(CalendarLessonDTO calendarLessonDTO);

}
