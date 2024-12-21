package com.backend.studycenter.common.dto.education;

import com.backend.studycenter.common.dto.course.LessonDTO;
import com.backend.studycenter.common.model.person.Teacher;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class CalendarLessonDTO {
    private Long id;
    private LocalDateTime plannedLessonDateTime;
    private LocalDateTime actualLessonDateTime;
    private Teacher plannedTeacher;
    private Teacher actualTeacher;
    @JsonProperty("lesson")
    private LessonDTO lessonDTO;
    private String comment;

    public CalendarLessonDTO() {
    }

    public CalendarLessonDTO(Long id, LocalDateTime plannedLessonDateTime, LocalDateTime actualLessonDateTime, Teacher plannedTeacher, Teacher actualTeacher, LessonDTO lessonDTO, String comment) {
        this.id = id;
        this.plannedLessonDateTime = plannedLessonDateTime;
        this.actualLessonDateTime = actualLessonDateTime;
        this.plannedTeacher = plannedTeacher;
        this.actualTeacher = actualTeacher;
        this.lessonDTO = lessonDTO;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPlannedLessonDateTime() {
        return plannedLessonDateTime;
    }

    public void setPlannedLessonDateTime(LocalDateTime plannedLessonDateTime) {
        this.plannedLessonDateTime = plannedLessonDateTime;
    }

    public LocalDateTime getActualLessonDateTime() {
        return actualLessonDateTime;
    }

    public void setActualLessonDateTime(LocalDateTime actualLessonDateTime) {
        this.actualLessonDateTime = actualLessonDateTime;
    }

    public Teacher getPlannedTeacher() {
        return plannedTeacher;
    }

    public void setPlannedTeacher(Teacher plannedTeacher) {
        this.plannedTeacher = plannedTeacher;
    }

    public Teacher getActualTeacher() {
        return actualTeacher;
    }

    public void setActualTeacher(Teacher actualTeacher) {
        this.actualTeacher = actualTeacher;
    }

    public LessonDTO getLessonDTO() {
        return lessonDTO;
    }

    public void setLessonDTO(LessonDTO lessonDTO) {
        this.lessonDTO = lessonDTO;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
