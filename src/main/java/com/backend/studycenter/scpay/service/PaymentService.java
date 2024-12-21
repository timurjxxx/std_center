package com.backend.studycenter.scpay.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.model.education.Group;
import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.common.service.education.GroupService;
import com.backend.studycenter.common.service.person.StudentService;
import com.backend.studycenter.scpay.dto.request.PaymentRequestDTO;
import com.backend.studycenter.scpay.dto.response.PaymentResponseDTO;
import com.backend.studycenter.scpay.mapper.PaymentMapper;
import com.backend.studycenter.scpay.model.Payment;
import com.backend.studycenter.scpay.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final StudentService studentService;
    private final GroupService groupService;

    public PaymentService(PaymentRepository paymentRepository,
                          StudentService studentService,
                          GroupService groupService) {
        this.paymentRepository = paymentRepository;
        this.studentService = studentService;
        this.groupService = groupService;
    }

    public ResponseEntity<PaymentResponseDTO> create(PaymentRequestDTO requestDTO) throws EntityNotFoundException {

        BigDecimal amount = new BigDecimal(requestDTO.getAmount());
        if (amount.intValue() < 0) {
            throw new RuntimeException("payment amount cannot be negative ");
        }

        Student student = studentService.getStudentById(requestDTO.getStudentId());
        Group group = groupService.getGroupById(requestDTO.getGroupId());
        Payment payment = PaymentMapper.reqestToModle(requestDTO, student, group);
        Payment savedPayment = paymentRepository.save(payment);
        PaymentResponseDTO responseDTO = PaymentMapper.modelToResponse(payment);

        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<List<PaymentResponseDTO>> allByStudentGroupId(Long studentId, Long groupId) {
        return ResponseEntity.ok(responseByStudentGroupId(studentId, groupId));
    }

    public ResponseEntity<List<PaymentResponseDTO>> allByStudentId(Long studentId) {
        List<Payment> payments = paymentRepository.findAllByStudentId(studentId);

        if (payments.isEmpty())
            throw new RuntimeException("any payment with student id %s not found ".formatted(studentId));

        List<PaymentResponseDTO> responseDTOS = new ArrayList<>();

        for (Payment payment : payments) {
            responseDTOS.add(PaymentMapper.modelToResponse(payment));
        }
        return ResponseEntity.ok(responseDTOS);
    }

    public ResponseEntity<String> delete(Long id) throws EntityNotFoundException {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);

        if (paymentOptional.isEmpty())
            throw new EntityNotFoundException("payment with id %s not found ".formatted(id));

        paymentRepository.delete(paymentOptional.get());

        return ResponseEntity.ok("payment successfully deleted ");
    }

    public List<PaymentResponseDTO> allPaymentByStudentId(Long studentId) {
        List<Payment> payments = paymentRepository.findAllByStudentId(studentId);

        List<PaymentResponseDTO> responseDTOS = new ArrayList<>();

        for (Payment payment : payments) {
            responseDTOS.add(PaymentMapper.modelToResponse(payment));
        }
        return responseDTOS;
    }

    public List<PaymentResponseDTO> responseByStudentGroupId(Long studentId, Long groupId) {
        List<Payment> payments = paymentRepository.findByStudentAndGroupId(studentId, groupId);

        if (payments.isEmpty())
            throw new RuntimeException("any payment with group id %s and student id %s not found ".formatted(groupId, studentId));

        List<PaymentResponseDTO> responseDTOS = new ArrayList<>();

        for (Payment payment : payments) {
            responseDTOS.add(PaymentMapper.modelToResponse(payment));
        }
        return responseDTOS;
    }
}
