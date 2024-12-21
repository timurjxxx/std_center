package com.backend.studycenter.sccontrol.dto;

import com.backend.studycenter.common.model.person.Interview;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.backend.studycenter.scpay.constant.AppConstants.DATETIME_FORMAT;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewResultDTO {
    private Long id;
    private Interview interview;
    private String details;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime date;
    private String feedback;
}
