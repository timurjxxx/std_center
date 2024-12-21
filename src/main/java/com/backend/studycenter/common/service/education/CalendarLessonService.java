package com.backend.studycenter.common.service.education;

import com.backend.studycenter.common.dto.education.CalendarLessonDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.education.CalendarLessonMapper;
import com.backend.studycenter.common.model.education.CalendarLesson;
import com.backend.studycenter.common.repository.education.CalendarLessonRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarLessonService {
    @Autowired
    private CalendarLessonRepository calendarLessonRepository;

    public ArrayList<CalendarLesson> getCalendarLessons() {
        return (ArrayList<CalendarLesson>) calendarLessonRepository.findAll();
    }

    public CalendarLesson getCalendarLessonById(Long calendarLessonId) throws EntityNotFoundException {
        CalendarLesson calendarLesson = calendarLessonRepository.findById(calendarLessonId)
                .orElseThrow(() -> new EntityNotFoundException("Not found calendarLesson by id = " + calendarLessonId));
        return calendarLesson;
    }

    public CalendarLesson addCalendarLesson(CalendarLessonDTO calendarLessonDTO) {
        return calendarLessonRepository.save(CalendarLessonMapper.INSTANCE.toModel(calendarLessonDTO));
    }

    public void deleteCalendarLesson(Long calendarLessonId) throws EntityNotFoundException {
        CalendarLesson calendarLesson = calendarLessonRepository.findById(calendarLessonId)
                .orElseThrow(() -> new EntityNotFoundException("Not found calendarLesson by id = " + calendarLessonId));
        calendarLessonRepository.delete(calendarLesson);
    }

    public CalendarLesson updateCalendarLesson(CalendarLessonDTO calendarLessonDTO, Long calendarLessonId) throws EntityNotFoundException {
        CalendarLesson existingCalendarLesson = calendarLessonRepository.findById(calendarLessonId)
                .orElseThrow(() -> new EntityNotFoundException("Not found calendarLesson by id = " + calendarLessonId));
        CalendarLesson updatedCalendarLesson = CalendarLessonMapper.INSTANCE.toModel(calendarLessonDTO);
        updatedCalendarLesson.setId(existingCalendarLesson.getId());
        return calendarLessonRepository.save(updatedCalendarLesson);
    }

    public void clear() {
        calendarLessonRepository.deleteAll();
    }
}

