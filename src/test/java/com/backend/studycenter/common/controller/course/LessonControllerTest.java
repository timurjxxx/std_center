package com.backend.studycenter.common.controller.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.backend.studycenter.common.model.course.Lesson;
import com.backend.studycenter.common.service.course.LessonService;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LessonControllerTest {


  @Mock
  private LessonService lessonService;

  @InjectMocks
  private LessonController lessonController;

  @BeforeEach
  void init() {
    MockitoAnnotations.initMocks(this);

  }

  @Test
  void getLessons() {
    ArrayList<Lesson> lessons = new ArrayList<>();
    Lesson lesson1 = new Lesson(1L, "Lesson1", "Description1");
    Lesson lesson2 = new Lesson(2L, "Lesson2", "Description2");
    Lesson lesson3 = new Lesson(3L, "Lesson3", "Description3");
    lessons.add(lesson1);
    lessons.add(lesson2);
    lessons.add(lesson3);

    when(lessonService.getLessons()).thenReturn(lessons);

    var lessonList = lessonController.getLessons();
    assertNotNull(lessonList);

    assertEquals("200 OK", lessonList.getStatusCode().toString());
    assertNotNull( lessonList.getBody());
    assertEquals(3, lessonList.getBody().size());
    assertEquals("Lesson1", lessonList.getBody().get(0).getName());
    assertEquals("Description1", lessonList.getBody().get(0).getDescription());

  }
}