package com.backend.studycenter.common.mapper.education;

import com.backend.studycenter.common.dto.education.GroupDTO;
import com.backend.studycenter.common.model.education.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(source = "teacher", target = "teacherDTO")
    @Mapping(source = "weekDayAndTimes", target = "weekDayAndTimeDTOs")
    @Mapping(source = "course", target = "courseDTO")
    @Mapping(source = "course.lessons", target = "courseDTO.lessonDTOs")
    @Mapping(source = "course.technologies", target = "courseDTO.technologyDTOs")
    @Mapping(source = "students", target = "studentDTOs")
    @Mapping(source = "calendarLessons", target = "calendarLessonDTOs")
//    @Mapping(source = "calendarLessons.lesson", target = "calendarLessonDTOs.lessonDTO")
    GroupDTO toDTO(Group group);

    @Mapping(source = "teacherDTO", target = "teacher")
    @Mapping(source = "weekDayAndTimeDTOs", target = "weekDayAndTimes")
    @Mapping(source = "courseDTO", target = "course")
    @Mapping(source = "courseDTO.lessonDTOs", target = "course.lessons")
    @Mapping(source = "courseDTO.technologyDTOs", target = "course.technologies")
    @Mapping(source = "studentDTOs", target = "students")
    @Mapping(source = "calendarLessonDTOs", target = "calendarLessons")
//    @Mapping(source = "calendarLessonDTOs.lessonDTO", target = "calendarLessons.lesson")
    Group toModel(GroupDTO groupDTO);
}
