package com.backend.studycenter.sccontrol.service;

import com.backend.studycenter.common.model.course.Course;
import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.common.repository.course.CourseRepository;
import com.backend.studycenter.common.repository.person.StudentRepository;
import com.backend.studycenter.sc_trainerize.repository.result.TestResultRepository;
import com.backend.studycenter.sccontrol.model.AcademicProgress;
import com.backend.studycenter.sccontrol.repository.AcademicProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AcademicProgressService {
@Autowired
    TestResultRepository testResultRepository;
@Autowired
    StudentRepository studentRepository;
@Autowired
    CourseRepository courseRepository;
@Autowired
    AcademicProgressRepository academicProgressRepository;

// for this part  active integration with team4 ExamresultService part  is needed. So for now some logic close to real one is being done.
    public AcademicProgress getAcademicProgress(Long courseID, Long studentID) {
        Course course =courseRepository.findById(courseID).orElseThrow(()-> new RuntimeException("Course does not exist with id "+courseID));
        Student student =studentRepository.findById(studentID).orElseThrow(()->new RuntimeException("Student does not exist with id "+studentID));
        AcademicProgress academicProgress =academicProgressRepository.findByCourseAndStudent(course,student);
        if (academicProgress==null){
            AcademicProgress academicProgress1 =new AcademicProgress();
            academicProgress1.setCourse(course);
            academicProgress1.setStudent(student);
            return academicProgress1;
        }
        return academicProgress;

    }
}
