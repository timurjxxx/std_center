package com.backend.studycenter.scpay.dto.response;

import com.backend.studycenter.common.dto.person.TeacherDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ContractResponseDTO {
    @JsonProperty("studentName")
    private String studentName;
    @JsonProperty("groupName")
    private String groupName;
    @JsonProperty("paymentAmount")
    private BigDecimal paymentAmount;
    @JsonProperty("courseDuration")
    private Integer durationOfCourse;
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
    @JsonProperty("subcontractList")
    private List<SubcontractResponseDTO> responseDTOList;


}