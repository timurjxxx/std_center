package com.backend.studycenter.scteach.dto;

import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.common.model.person.Teacher;
import com.backend.studycenter.scteach.model.Homework;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkResultDTO {

    private Long id;
    private Student studentId;
    private Homework homeworkId;
    private Teacher teacherID;
    private Double score;
    private String description;
}
