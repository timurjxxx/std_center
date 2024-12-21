package com.backend.studycenter.scpay.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DebtResponseDTO {
    private List<SubcontractResponseDTO> subcontractResponseDTOS;

    private List<PaymentResponseDTO> paymentResponseDTOS;

    private BigDecimal debtAmount;
}
