package com.backend.studycenter.sc_trainerize.model.test;

import com.backend.studycenter.common.model.security.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.backend.studycenter.scpay.constant.AppConstants.DATETIME_FORMAT;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test_result")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "score")
    private double score;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "testResult", cascade = CascadeType.ALL)
    private List<AnswerResult> answerResults;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "time_taken")
    private long timeTaken;

    @Column(name = "remarks")
    private String remarks;

}

