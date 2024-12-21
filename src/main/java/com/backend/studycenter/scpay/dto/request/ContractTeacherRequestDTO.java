package com.backend.studycenter.scpay.dto.request;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractTeacherRequestDTO {
    private Long teacherId;

    private Long duration;

    private String teacherSalary;

    private String contractConditions;

    private Boolean isTeacherAccepted;
}
