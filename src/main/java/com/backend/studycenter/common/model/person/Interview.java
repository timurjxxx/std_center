package com.backend.studycenter.common.model.person;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Person candidate;
    @ManyToOne
    @JoinColumn(name = "interviewer_id", nullable = false)
    private Person interviewer;
    private LocalDateTime plannedDateTime;
    private LocalDateTime actualDateTime;
    private Integer discountPercent;
}
