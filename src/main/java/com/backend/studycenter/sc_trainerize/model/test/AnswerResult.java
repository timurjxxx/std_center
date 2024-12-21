package com.backend.studycenter.sc_trainerize.model.test;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "answer_result")
public class AnswerResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_result_id")
    private TestResult testResult;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "chosen_answer")
    private String chosenAnswer;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @Column(name = "response_time")
    private long responseTime;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "points_earned")
    private double pointsEarned;

    @Column(name = "feedback")
    private String feedback;

}

