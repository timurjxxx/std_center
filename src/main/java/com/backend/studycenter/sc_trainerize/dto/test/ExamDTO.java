package com.backend.studycenter.sc_trainerize.dto.test;

import com.backend.studycenter.sc_trainerize.model.topic.Module;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDTO {
    private Long id;

    private LocalDateTime date;

    private String typeOfExam;

    private List<Module> modules;


}
