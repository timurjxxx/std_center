package com.backend.studycenter.scteach.dto;

import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.scteach.model.Assessment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomeworkDTO {
    private Long id;
    private Student studentId;
    private Assessment assessmentID;
    private String file;//upload file
}
