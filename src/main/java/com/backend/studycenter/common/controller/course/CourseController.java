package com.backend.studycenter.common.controller.course;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.dto.course.CourseDTO;
import com.backend.studycenter.common.dto.course.CourseInfoDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.course.CourseMapper;
import com.backend.studycenter.common.model.course.Course;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.common.service.course.CourseInfoService;
import com.backend.studycenter.common.service.course.CourseService;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseInfoService courseInfoService;

    @GetMapping(value = "/api/v1/courses")
    public ResponseEntity<ArrayList<CourseDTO>> getCourses() {
        ArrayList<Course> courses = courseService.  getCourses();
        ArrayList<CourseDTO> courseDTOs = new ArrayList<>();
        for (Course course : courses) {
            courseDTOs.add(CourseMapper.toDTO(course));
        }
        if (courseDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(courseDTOs);
        }
    }

    @GetMapping(value = "/api/v1/courses/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable(name = "id") Long courseId) throws EntityNotFoundException {
        CourseDTO courseDTO = CourseMapper.toDTO(courseService.getCourseById(courseId));
        if (courseDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(courseDTO);
        }
    }

    @GetMapping(value = "/api/v1/courses/info/{id}")
    public ResponseEntity<CourseInfoDTO> getCourseInfoById(@PathVariable(name = "id") Long courseId) throws EntityNotFoundException {
        CourseInfoDTO courseInfoDTO = courseInfoService.getCourseInfoDTO(courseId);
        if (courseInfoDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(courseInfoDTO);
        }
    }

    @PostMapping(value = "/api/v1/courses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        try {
            return ResponseEntity.ok(CourseMapper.toDTO(courseService.addCourse(courseDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PutMapping(value = "/api/v1/courses/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable(name = "id") Long courseId) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(CourseMapper.toDTO(courseService.updateCourse(courseDTO, courseId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @DeleteMapping(value = "/api/v1/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable(name = "id") Long courseId) throws EntityNotFoundException {
        try {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
