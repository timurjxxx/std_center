package com.backend.studycenter.scteach.dto;

import com.backend.studycenter.common.model.education.CalendarLesson;
import com.backend.studycenter.common.model.education.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {
    private Long id;
    private Group groupID;
    private CalendarLesson calendarLessonID;
}
