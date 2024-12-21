package com.backend.studycenter.sccontrol.model;

import com.backend.studycenter.common.model.course.Course;
import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.sc_trainerize.model.test.TestResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "academic_progress")
public class AcademicProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Course course;
    @ManyToOne
    private Student student;
    @OneToMany
    private List<TestResult> testResults;

    //TestResult model is changed
    public AcademicProgress(Long id, Course course, Student student, List<TestResult> testResults) {
        this.id = id;
        this.course = course;
        this.student = student;
        this.testResults = testResults;
    }

    public AcademicProgress() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<TestResult> getAcademicProgresses() {
        return testResults;
    }

    public void setAcademicProgresses(List<TestResult> academicProgresses) {
        this.testResults = academicProgresses;
    }

}
