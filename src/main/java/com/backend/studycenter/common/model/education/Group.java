package com.backend.studycenter.common.model.education;

import com.backend.studycenter.common.model.course.Course;
import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.common.model.person.Teacher;
import com.backend.studycenter.common.service.Validator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany
    private List<WeekDayAndTime> weekDayAndTimes;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany
    private List<Student> students;
    private LocalDate startDate;
    @OneToMany
    private List<CalendarLesson> calendarLessons;

    public Group() {
    }

    public Group(Long id, String name, String description, Teacher teacher, List<WeekDayAndTime> weekDayAndTimes, Course course, List<Student> students, LocalDate startDate, List<CalendarLesson> calendarLessons) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.weekDayAndTimes = weekDayAndTimes;
        this.course = course;
        this.students = students;
        this.startDate = startDate;
        this.calendarLessons = calendarLessons;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<WeekDayAndTime> getWeekDayAndTimes() {
        return weekDayAndTimes;
    }

    public void setWeekDayAndTimes(List<WeekDayAndTime> weekDayAndTimes) {
        this.weekDayAndTimes = weekDayAndTimes;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<CalendarLesson> getCalendarLessons() {
        return calendarLessons;
    }

    public void setCalendarLessons(List<CalendarLesson> calendarLessons) {
        this.calendarLessons = calendarLessons;
    }


    @Override
    public String validate() {
        String err = "";
        if (this.getStartDate() == null) {
            err = "StartDate is null!";
        } else if (this.getWeekDayAndTimes().isEmpty()) {
            err = "List<WeekDayAndTime> is empty!";
        } else if (this.getTeacher() == null) {
            err = "Teacher is null!";
        } else if (this.getCourse() == null) {
            err = "Course is null!";
        } else if (this.getCourse().getLessons().isEmpty()) {
            err = "List<Lesson> is empty!";
        }

        if (!err.isBlank()) {
            return "Not valid group with id = " + this.getId() + ". " + err;
        }
        return err;
    }
}
