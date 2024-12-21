package com.backend.studycenter.sccontrol.dto;

import com.backend.studycenter.common.dto.course.CourseDTO;
import com.backend.studycenter.common.dto.person.StudentDTO;
import com.backend.studycenter.common.model.course.Course;
import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.sc_trainerize.dto.test.TestResultDTO;
import com.backend.studycenter.sc_trainerize.model.test.TestResult;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor

public class AcademicProgressDTO {
    private Long id;

    private CourseDTO course;
    private StudentDTO student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public List<TestResultDTO> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<TestResultDTO> testResults) {
        this.testResults = testResults;
    }

    private List<TestResultDTO> testResults;
}
