package com.backend.studycenter.common.service.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.course.LessonMapper;
import com.backend.studycenter.common.model.course.Lesson;
import com.backend.studycenter.common.repository.course.LessonRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LessonServiceTest {

  @InjectMocks
  LessonService lessonService;

  @Mock
  LessonRepository lessonRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

  }

  @Test
  void getLessons() {
    List<Lesson> lessons = new ArrayList<>();
    var lesson1 = new Lesson(1L, "Lesson1", "Description1");
    Lesson lesson2 = new Lesson(2L, "Lesson2", "Description2");
    Lesson lesson3 = new Lesson(3L, "Lesson3", "Description3");
    lessons.add(lesson1);
    lessons.add(lesson2);
    lessons.add(lesson3);

    when(lessonRepository.findAll()).thenReturn(lessons);

    List<Lesson> lessonList = lessonService.getLessons();
    assertNotNull(lessonList);
    assertEquals(3, lessons.size());
    assertEquals("Lesson1", lessonList.get(0).getName());
    assertEquals("Description1", lessonList.get(0).getDescription());

    assertEquals("Lesson2", lessonList.get(1).getName());
    assertEquals("Description2", lessonList.get(1).getDescription());


  }

  @Test
  void getLessonById() throws EntityNotFoundException {
    var lessonOptional1 = Optional.of(new Lesson(1L, "Lesson1", "Description1"));
    var lessonOptional2 = Optional.of(new Lesson(1L, "Lesson2", "Description2"));
    when(lessonRepository.findById(1L)).thenReturn(lessonOptional1);
    when(lessonRepository.findById(2L)).thenReturn(lessonOptional2);

    var lesson1 = lessonService.getLessonById(1L);
    var lesson2 = lessonService.getLessonById(2L);
    assertNotNull(lesson1);
    assertNotNull(lesson2);
    assertEquals("Lesson1", lesson1.getName());
    assertEquals("Lesson2", lesson2.getName());
    assertEquals("Description1", lesson1.getDescription());
    assertEquals("Description2", lesson2.getDescription());

  }

  @Test
  void createLesson() {
    Lesson lesson1 = new Lesson(1L, "Lesson1", "Description1");
    Lesson lesson2 = new Lesson(2L, "Lesson1", "Description1");
    var dto1 = LessonMapper.toDTO(lesson1);
    var dto2 = LessonMapper.toDTO(lesson1);

    lessonService.addLesson(dto1);
    lessonService.addLesson(dto2);

    verify(lessonRepository, times(2)).save(any());

  }
}