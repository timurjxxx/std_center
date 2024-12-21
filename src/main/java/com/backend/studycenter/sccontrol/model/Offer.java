package com.backend.studycenter.sccontrol.model;

import com.backend.studycenter.sccontrol.model.Enummration.OfferStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.backend.studycenter.scpay.constant.AppConstants.DATETIME_FORMAT;

@Entity
@Table(name = "offer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    @JoinColumn(name = "interview_result_id", nullable = false)
    private InterviewResult interviewResult;
    @Enumerated(EnumType.STRING)
    private OfferStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime date;
    private String reason;
    private String feedback;
}
