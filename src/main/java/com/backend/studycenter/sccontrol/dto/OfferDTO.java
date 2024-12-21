package com.backend.studycenter.sccontrol.dto;

import com.backend.studycenter.sccontrol.model.Enummration.OfferStatus;
import com.backend.studycenter.sccontrol.model.InterviewResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.backend.studycenter.scpay.constant.AppConstants.DATETIME_FORMAT;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDTO {
    private Long id;
    @OneToOne()
    @JoinColumn(name = "interview_result_id", nullable = false)
    private InterviewResult interviewResult;
    private OfferStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime date;
    private String reason;
    private String feedback;
}
