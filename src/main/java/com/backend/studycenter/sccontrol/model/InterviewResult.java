package com.backend.studycenter.sccontrol.model;

import com.backend.studycenter.common.model.person.Interview;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.backend.studycenter.scpay.constant.AppConstants.DATETIME_FORMAT;

@Entity
@Table(name = "interview_result")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    @JoinColumn(name = "interview_id", nullable = false, unique = true)
    private Interview interview;
    @Column(nullable = false, length = 350)
    private String details;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime date;
    private String feedback;
}
