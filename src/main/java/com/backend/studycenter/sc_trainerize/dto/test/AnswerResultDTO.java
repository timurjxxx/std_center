package com.backend.studycenter.sc_trainerize.dto.test;

import lombok.Data;

@Data
public class AnswerResultDTO {

    private Long id;

    private Long questionId;

    private Long testResultId;

    private String chosenAnswer;

    private boolean isCorrect;

    private String explanation;

    private double pointsEarned;

    private String feedback;
}

