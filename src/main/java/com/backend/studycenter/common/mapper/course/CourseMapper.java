package com.backend.studycenter.common.mapper.course;

import com.backend.studycenter.common.dto.course.CourseDTO;
import com.backend.studycenter.common.dto.course.LessonDTO;
import com.backend.studycenter.common.dto.course.TechnologyDTO;
import com.backend.studycenter.common.dto.person.TeacherDTO;
import com.backend.studycenter.common.model.course.Course;
import com.backend.studycenter.common.model.course.Lesson;
import com.backend.studycenter.common.model.course.Technology;
import com.backend.studycenter.common.model.person.Teacher;
import java.util.ArrayList;
import java.util.List;

public class CourseMapper {
    public static CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setDescription(course.getDescription());
        dto.setDuration(course.getDuration());
        dto.setPrice(course.getPrice());

        List<LessonDTO> lessonDTOs = new ArrayList<>();
        for (Lesson lesson : course.getLessons()) {
            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setId(lesson.getId());
            lessonDTO.setName(lesson.getName());
            lessonDTO.setDescription(lesson.getDescription());
            lessonDTOs.add(lessonDTO);
        }
        dto.setLessonDTOs(lessonDTOs);

        List<TechnologyDTO> technologyDTOs = new ArrayList<>();
        for (Technology technology : course.getTechnologies()) {
            TechnologyDTO technologyDTO = new TechnologyDTO();
            technologyDTO.setId(technology.getId());
            technologyDTO.setName(technology.getName());
            technologyDTO.setDescription(technology.getDescription());
            technologyDTOs.add(technologyDTO);
        }

        List<TeacherDTO> teacherDTOs = new ArrayList<>();
        for (Teacher teacher : course.getTeachers()) {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(teacher.getId());
            teacherDTO.setName(teacher.getName());
            teacherDTO.setDescription(teacher.getDescription());
            teacherDTOs.add(teacherDTO);
        }
        dto.setTechnologyDTOs(technologyDTOs);

        return dto;
    }

    public static Course toModel(CourseDTO dto) {
        Course course = new Course();
        course.setId(dto.getId());
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setDuration(dto.getDuration());
        course.setPrice(dto.getPrice());

        List<Lesson> lessons = new ArrayList<>();
        for (LessonDTO lessonDTO : dto.getLessonDTOs()) {
            Lesson lesson = new Lesson();
            lesson.setId(lessonDTO.getId());
            lesson.setName(lessonDTO.getName());
            lesson.setDescription(lessonDTO.getDescription());
            lessons.add(lesson);
        }
        course.setLessons(lessons);

        List<Technology> technologies = new ArrayList<>();
        for (TechnologyDTO technologyDTO : dto.getTechnologyDTOs()) {
            Technology technology = new Technology();
            technology.setId(technologyDTO.getId());
            technology.setName(technologyDTO.getName());
            technology.setDescription(technologyDTO.getDescription());
            technologies.add(technology);
        }
        course.setTechnologies(technologies);

        return course;
    }

    public static Course toModel(CourseDTO dto, Course course) {
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());
        course.setDuration(dto.getDuration());
        course.setPrice(dto.getPrice());

        List<Lesson> lessons = new ArrayList<>();
        for (LessonDTO lessonDTO : dto.getLessonDTOs()) {
            Lesson lesson = new Lesson();
            lesson.setId(lessonDTO.getId());
            lesson.setName(lessonDTO.getName());
            lesson.setDescription(lessonDTO.getDescription());
            lessons.add(lesson);
        }
        course.setLessons(lessons);

        List<Technology> technologies = new ArrayList<>();
        for (TechnologyDTO technologyDTO : dto.getTechnologyDTOs()) {
            Technology technology = new Technology();
            technology.setId(technologyDTO.getId());
            technology.setName(technologyDTO.getName());
            technology.setDescription(technologyDTO.getDescription());
            technologies.add(technology);
        }
        course.setTechnologies(technologies);

        return course;
    }
}
