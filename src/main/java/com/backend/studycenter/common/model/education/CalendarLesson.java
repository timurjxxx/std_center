package com.backend.studycenter.common.model.education;

import com.backend.studycenter.common.model.course.Lesson;
import com.backend.studycenter.common.model.person.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "calendar_lesson")
public class CalendarLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime plannedLessonDateTime;
    private LocalDateTime actualLessonDateTime;
    @OneToOne
    private Teacher plannedTeacher;
    @OneToOne
    private Teacher actualTeacher;
    @OneToOne
    private Lesson lesson;
    private String comment;

    public CalendarLesson() {
    }

    public CalendarLesson(Long id, LocalDateTime plannedLessonDateTime, LocalDateTime actualLessonDateTime, Teacher plannedTeacher, Teacher actualTeacher, Lesson lesson, String comment) {
        this.id = id;
        this.plannedLessonDateTime = plannedLessonDateTime;
        this.actualLessonDateTime = actualLessonDateTime;
        this.plannedTeacher = plannedTeacher;
        this.actualTeacher = actualTeacher;
        this.lesson = lesson;
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

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
