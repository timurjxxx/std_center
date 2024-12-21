package com.backend.studycenter.common.service.course;

import com.backend.studycenter.common.dto.course.LessonDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.course.LessonMapper;
import com.backend.studycenter.common.model.course.Course;
import com.backend.studycenter.common.model.course.Lesson;
import com.backend.studycenter.common.repository.course.LessonRepository;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseService courseService;

    public ArrayList<Lesson> getLessons() {
        return (ArrayList<Lesson>) lessonRepository.findAll();
    }

    public ArrayList<Lesson> getLessonsByCourseId(Long courseId) throws EntityNotFoundException {
        ArrayList<Lesson> lessons = new ArrayList<>();
        Course course = courseService.getCourseById(courseId);
        for (Lesson lesson : course.getLessons()) {
            lessons.add(lesson);
        }
        return lessons;
    }

    public Lesson getLessonById(Long lessonId) throws EntityNotFoundException {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Not found lesson with id = " + lessonId));
        return lesson;
    }


    public Lesson addLesson(LessonDTO lessonDTO) {
        return lessonRepository.save(LessonMapper.toModel(lessonDTO));
    }

    public void deleteLesson(Long lessonId) throws EntityNotFoundException {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new EntityNotFoundException("Not found lesson with id = " + lessonId));
        lessonRepository.delete(lesson);
    }

    public Lesson updateLesson(LessonDTO lessonDTO, Long lessonId) throws EntityNotFoundException {
        Optional<Lesson> lessonOptional = lessonRepository.findById(lessonId);
        if (lessonOptional.isEmpty()) {
            throw new EntityNotFoundException("Not found lesson with id = " + lessonId);
        } else {
            Lesson lesson = lessonOptional.get();
            return lessonRepository.save(LessonMapper.toModel(lessonDTO, lesson));
        }
    }

    public void clear() {
        lessonRepository.deleteAll();
    }
}

