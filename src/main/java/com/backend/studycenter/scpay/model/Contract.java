package com.backend.studycenter.scpay.model;

import com.backend.studycenter.common.model.education.Group;
import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.common.model.person.Teacher;
import com.backend.studycenter.scpay.enums.ContractStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "contract")
public class Contract {
    // fields from scpayment module
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "enrollment_id")
    //private Enrollment enrollment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "duration_of_course")
    private Integer durationOfCourse;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    // attributes from sccontrol module

//    private LocalDateTime startTime; // TODO: need to think about this

    private Long duration;

    private String teacherSalary;

    private String contractConditions;

    private Boolean isTeacherAccepted;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}