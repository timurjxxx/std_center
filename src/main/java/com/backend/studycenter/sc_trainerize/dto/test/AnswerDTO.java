package com.backend.studycenter.sc_trainerize.dto.test;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private Long id;

    private String answerText;

    private boolean isTrue;

}
