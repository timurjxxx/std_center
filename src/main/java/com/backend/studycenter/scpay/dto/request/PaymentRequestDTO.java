package com.backend.studycenter.scpay.dto.request;


import com.backend.studycenter.scpay.enums.Status;
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
public class PaymentRequestDTO {
    private Long studentId;
    private Long groupId;
    private String amount;
    private  Status status;
    private String paymentType;
}