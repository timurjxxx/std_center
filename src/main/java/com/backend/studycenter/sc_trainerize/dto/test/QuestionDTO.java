package com.backend.studycenter.sc_trainerize.dto.test;

import com.backend.studycenter.sc_trainerize.model.enumClasses.TypeQuestions;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long id;

    private String text;

    private String mark;

    private String variantId;

    private TypeQuestions typeQuestions;

    private List<AnswerDTO> answerDTOS;
}
