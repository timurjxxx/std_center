package com.backend.studycenter.scpay.mapper;

import com.backend.studycenter.common.model.education.Group;
import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.scpay.dto.PaymentDTO;
import com.backend.studycenter.scpay.dto.request.PaymentRequestDTO;
import com.backend.studycenter.scpay.dto.response.PaymentResponseDTO;
import com.backend.studycenter.scpay.enums.PaymentType;
import com.backend.studycenter.scpay.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    static Payment reqestToModle(PaymentRequestDTO requestDTO, Student student, Group group) {


        BigDecimal amount=BigDecimal.valueOf(Double.valueOf(requestDTO.getAmount()));

      return   Payment.builder()
                .student(student)
                .group(group)
                .amount(amount)
                .paymentType(PaymentType.valueOf(requestDTO.getPaymentType()))
                .status(requestDTO.getStatus())
                .isActive(true)
                .createdAt(LocalDateTime.now()).build();

    }

    static PaymentResponseDTO modelToResponse(Payment payment) {
        return PaymentResponseDTO.builder()
                .studentName(payment.getStudent().getName())
                .groupName(payment.getGroup().getName())
                .amount(payment.getAmount())
                .id(payment.getId())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();

    }

    //  @Mapping(source = "payment",target = "paymentDTO")
    PaymentDTO toDTO(Payment payment);

  //  @Mapping(source = "paymentDTO",target = "payment")
    Payment toModel(PaymentDTO paymentDTO);
}
