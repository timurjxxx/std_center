package com.backend.studycenter.scpay.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import com.backend.studycenter.common.dto.person.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractRequestDTO {
    private Long studentId;
    private Long groupId;
    private String paymentAmount;
    private Integer durationOfCourse;
    private List<SubcontractRequestDTO> subcontractRequests;

    //


}