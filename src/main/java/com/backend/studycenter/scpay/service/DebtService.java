package com.backend.studycenter.scpay.service;


import java.math.BigDecimal;
import java.util.List;

import com.backend.studycenter.scpay.dto.response.DebtResponseDTO;
import com.backend.studycenter.scpay.dto.response.PaymentResponseDTO;
import com.backend.studycenter.scpay.dto.response.SubcontractResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DebtService {
    private final SubcontractService subcontractService;
    private final PaymentService paymentService;

    public DebtService(SubcontractService subcontractService,
                       PaymentService paymentService) {
        this.subcontractService = subcontractService;
        this.paymentService = paymentService;
    }

    public ResponseEntity<DebtResponseDTO> getByStudentId(Long studentId) {
        List<SubcontractResponseDTO> subcontractResponseDTO = subcontractService.getByStudentId(studentId);
        List<PaymentResponseDTO> paymentResponseDTOs = paymentService.allPaymentByStudentId(studentId);


        BigDecimal debtAmount = new BigDecimal(0);
        BigDecimal paymentAmount = new BigDecimal(0);

        for (SubcontractResponseDTO responseDTO : subcontractResponseDTO) {
            debtAmount = debtAmount.add(responseDTO.getAmountEachMonth());
        }

        for (PaymentResponseDTO payment : paymentResponseDTOs) {
            paymentAmount = paymentAmount.add(payment.getAmount());
        }
        debtAmount = debtAmount.subtract(paymentAmount);
        if (debtAmount.intValue() <= 0)
            debtAmount = BigDecimal.ZERO;
        DebtResponseDTO debtResponseDTO = DebtResponseDTO.builder()
                .subcontractResponseDTOS(subcontractResponseDTO)
                .paymentResponseDTOS(paymentResponseDTOs)
                .debtAmount(debtAmount)
                .build();

        return ResponseEntity.ok(debtResponseDTO);
    }

    public ResponseEntity<DebtResponseDTO> getByStudentGroupId(Long studentId, Long groupId) {
        List<PaymentResponseDTO> paymentResponseDTOs = paymentService.responseByStudentGroupId(studentId, groupId);

        List<SubcontractResponseDTO> subcontractResponseDTO = subcontractService.getResponseByStudentGroupId(studentId, groupId);

        BigDecimal debtAmount = new BigDecimal(0);
        BigDecimal paymentAmount = new BigDecimal(0);

        for (SubcontractResponseDTO responseDTO : subcontractResponseDTO) {
            debtAmount = debtAmount.add(responseDTO.getAmountEachMonth());
        }

        for (PaymentResponseDTO payment : paymentResponseDTOs) {
            paymentAmount = paymentAmount.add(payment.getAmount());
        }

        debtAmount = debtAmount.subtract(paymentAmount);
        if (debtAmount.intValue() <= 0)
            debtAmount = BigDecimal.ZERO;
        DebtResponseDTO debtResponseDTO = DebtResponseDTO.builder()
                .subcontractResponseDTOS(subcontractResponseDTO)
                .paymentResponseDTOS(paymentResponseDTOs)
                .debtAmount(debtAmount)
                .build();

        return ResponseEntity.ok(debtResponseDTO);
    }
}