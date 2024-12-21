package com.backend.studycenter.scteach.model;

import com.backend.studycenter.common.model.education.CalendarLesson;
import com.backend.studycenter.common.model.education.Group;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "groupID")
    private Group groupID;
    @ManyToOne
    @JoinColumn(name = "calendarLessonID")
    private CalendarLesson calendarLessonID;
}
