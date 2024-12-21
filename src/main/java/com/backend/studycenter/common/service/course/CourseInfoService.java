package com.backend.studycenter.common.service.course;

import com.backend.studycenter.common.dto.course.CourseInfoDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.course.LessonMapper;
import com.backend.studycenter.common.mapper.course.TechnologyMapper;
import com.backend.studycenter.common.mapper.person.TeacherMapper;
import com.backend.studycenter.common.service.person.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseInfoService {
    @Autowired
    private CourseService courseService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private TechnologyService technologyService;
    @Autowired
    private TeacherService teacherService;

    public CourseInfoDTO getCourseInfoDTO(Long courseId) throws EntityNotFoundException {
        CourseInfoDTO courseInfoDTO = new CourseInfoDTO();

        if (courseService.getCourseById(courseId) == null) {
            throw new EntityNotFoundException("Not found course by id = " + courseId);
        }
        courseInfoDTO.setId(courseService.getCourseById(courseId).getId());
        courseInfoDTO.setName(courseService.getCourseById(courseId).getName());
        courseInfoDTO.setDescription(courseService.getCourseById(courseId).getDescription());
        courseInfoDTO.setDuration(courseService.getCourseById(courseId).getDuration());
        courseInfoDTO.setPrice(courseService.getCourseById(courseId).getPrice());
        courseInfoDTO.setLessonDTOs(LessonMapper.toDTO(lessonService.getLessonsByCourseId(courseId)));
        courseInfoDTO.setTechnologyDTOs(TechnologyMapper.toDTO(technologyService.getTechnologiesByCourseId(courseId)));
        courseInfoDTO.setTeacherDTOs(TeacherMapper.INSTANCE.toDTO(teacherService.getTeachersByCourseId(courseId)));
        return courseInfoDTO;
    }
}
