package com.backend.studycenter.common.service.course;

import static org.slf4j.LoggerFactory.getLogger;

import com.backend.studycenter.common.dto.course.CourseDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.course.CourseMapper;
import com.backend.studycenter.common.model.course.Course;
import com.backend.studycenter.common.repository.course.CourseRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {
    private final Logger logger = getLogger(getClass().getName());

    @Autowired
    private CourseRepository courseRepository;

//    @Cacheable("courses")
    public ArrayList<Course> getCourses() {
        return (ArrayList<Course>) courseRepository.findAll();
    }
    @Transactional // Add this annotation

    @Cacheable(value = "courses", key = "#courseId")
    public Course getCourseById(Long courseId) throws EntityNotFoundException {
        logger.info("course are retrieving from DB");
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Not found course with id = " + courseId));
        return course;
    }

    public Course addCourse(CourseDTO courseDTO) {
        return courseRepository.save(CourseMapper.toModel(courseDTO));
    }

    public void deleteCourse(Long courseId) throws EntityNotFoundException {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Not found course with id = " + courseId));
        courseRepository.delete(course);
    }

    public Course updateCourse(CourseDTO courseDTO, Long courseId) throws EntityNotFoundException {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isEmpty()) {
            throw new EntityNotFoundException("Not found course with id = " + courseId);
        } else {
            Course course = courseOptional.get();
            return courseRepository.save(CourseMapper.toModel(courseDTO, course));
        }
    }

    public void clear() {
        courseRepository.deleteAll();
    }
}
