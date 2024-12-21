package com.backend.studycenter.scpay.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.backend.studycenter.scpay.util.CustomDateDeserializer;
import com.backend.studycenter.scpay.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
public class SubcontractRequestDTO {
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private LocalDateTime dateOfMonth;
    private String amountEachMonth;
}