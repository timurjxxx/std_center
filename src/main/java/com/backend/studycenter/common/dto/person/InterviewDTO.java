package com.backend.studycenter.common.dto.person;

import com.backend.studycenter.common.model.person.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.backend.studycenter.scpay.constant.AppConstants.DATETIME_FORMAT;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewDTO {

    private Long id;
    private Person candidate;
    private Person interviewer;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime plannedDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime actualDateTime;
    private Integer discountPercent;
}
