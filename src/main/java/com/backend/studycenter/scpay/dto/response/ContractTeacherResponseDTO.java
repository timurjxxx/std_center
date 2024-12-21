package com.backend.studycenter.scpay.dto.response;

import com.backend.studycenter.scpay.util.CustomDateDeserializer;
import com.backend.studycenter.scpay.util.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)

public class ContractTeacherResponseDTO {
     @JsonProperty(value = "teacher_id")
     private Long teacherId;

     @JsonProperty(value = "duration")
     private Long duration;

     @JsonProperty(value = "teacherSalary")
     private String teacherSalary;

     @JsonProperty(value = "contractConditions")
     private String contractConditions;

     @JsonProperty(value = "isTeacherAccepted")
     private Boolean isTeacherAccepted;

     @JsonSerialize(using = CustomDateSerializer.class)
     @JsonDeserialize(using = CustomDateDeserializer.class)
     @JsonProperty(value = "updatedAt")
     private LocalDateTime updatedAt;

     @JsonSerialize(using = CustomDateSerializer.class)
     @JsonDeserialize(using = CustomDateDeserializer.class)
     @JsonProperty(value = "createdAt")
     private LocalDateTime createdAt;


}

