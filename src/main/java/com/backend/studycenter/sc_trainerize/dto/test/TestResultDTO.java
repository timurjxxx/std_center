package com.backend.studycenter.sc_trainerize.dto.test;

import java.util.List;
import lombok.Data;

@Data
public class TestResultDTO {

    private Long id;

    private Long userId;

    private Long testId;

    private double score;

    private String status;

    private List<AnswerResultDTO> answerResults;

    private String timestamp;

    private long timeTaken;

    private String remarks;

}

