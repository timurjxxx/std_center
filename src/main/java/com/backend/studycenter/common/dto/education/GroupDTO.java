package com.backend.studycenter.common.dto.education;

import com.backend.studycenter.common.dto.course.CourseDTO;
import com.backend.studycenter.common.dto.person.StudentDTO;
import com.backend.studycenter.common.dto.person.TeacherDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;

public class GroupDTO {
    private Long id;
    private String name;
    private String description;
    @JsonProperty("teacher")
    private TeacherDTO teacherDTO;
    @JsonProperty("weekDayAndTimes")
    private List<WeekDayAndTimeDTO> weekDayAndTimeDTOs;
    @JsonProperty("course")
    private CourseDTO courseDTO;
    @JsonProperty("students")
    private List<StudentDTO> studentDTOs;
    private LocalDate startDate;
    @JsonProperty("calendarLessons")
    private List<CalendarLessonDTO> calendarLessonDTOs;

    public GroupDTO() {
    }

    public GroupDTO(Long id, String name, String description, TeacherDTO teacherDTO, CourseDTO courseDTO, List<StudentDTO> studentDTOs, LocalDate startDate, List<WeekDayAndTimeDTO> weekDayAndTimeDTOs, List<CalendarLessonDTO> calendarLessonDTOs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teacherDTO = teacherDTO;
        this.courseDTO = courseDTO;
        this.studentDTOs = studentDTOs;
        this.startDate = startDate;
        this.weekDayAndTimeDTOs = weekDayAndTimeDTOs;
        this.calendarLessonDTOs = calendarLessonDTOs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TeacherDTO getTeacherDTO() {
        return teacherDTO;
    }

    public void setTeacherDTO(TeacherDTO teacherDTO) {
        this.teacherDTO = teacherDTO;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public List<StudentDTO> getStudentDTOs() {
        return studentDTOs;
    }

    public void setStudentDTOs(List<StudentDTO> studentDTOs) {
        this.studentDTOs = studentDTOs;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<WeekDayAndTimeDTO> getWeekDayAndTimeDTOs() {
        return weekDayAndTimeDTOs;
    }

    public void setWeekDayAndTimeDTOs(List<WeekDayAndTimeDTO> weekDayAndTimeDTOs) {
        this.weekDayAndTimeDTOs = weekDayAndTimeDTOs;
    }

    public List<CalendarLessonDTO> getCalendarLessonDTOs() {
        return calendarLessonDTOs;
    }

    public void setCalendarLessonDTOs(List<CalendarLessonDTO> calendarLessonDTOs) {
        this.calendarLessonDTOs = calendarLessonDTOs;
    }
}
