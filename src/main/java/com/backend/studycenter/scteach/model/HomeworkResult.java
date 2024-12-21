package com.backend.studycenter.scteach.model;

import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.common.model.person.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "homework_result")
public class HomeworkResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student studentId;
    @OneToOne
    private Homework homeworkId;
    @ManyToOne
    private Teacher teacherID;
    private Double score;
    private String description;
}
