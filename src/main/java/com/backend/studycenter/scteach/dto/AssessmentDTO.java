package com.backend.studycenter.scteach.dto;

import com.backend.studycenter.common.model.education.Group;
import com.backend.studycenter.common.model.person.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentDTO {
    private Long id;
    private Teacher teacherID;
    private String description;
    private Group groupID;
}
