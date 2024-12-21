package com.backend.studycenter.sc_trainerize.dto.test;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {
    private Long id;

    private String name;

    private List<QuestionDTO> questionDTOS;
}
