package com.backend.studycenter.scpay.controller;


import com.backend.studycenter.common.exception.EntityNotFoundException;
import static com.backend.studycenter.scpay.constant.AppConstants.API_VERSION;
import com.backend.studycenter.scpay.dto.request.PaymentRequestDTO;
import com.backend.studycenter.scpay.dto.response.PaymentResponseDTO;
import com.backend.studycenter.scpay.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = API_VERSION + "/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PaymentResponseDTO> create(@RequestBody PaymentRequestDTO requestDTO) throws EntityNotFoundException {
        return paymentService.create(requestDTO);
    }

    @GetMapping(value = "/{studentId}/{groupId}")
    public ResponseEntity<List<PaymentResponseDTO>> getByStudentGroupId(@PathVariable Long studentId, @PathVariable Long groupId) {
        return paymentService.allByStudentGroupId(studentId, groupId);
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<List<PaymentResponseDTO>> getByStudentId(@PathVariable("studentId") Long studentId) {
        return paymentService.allByStudentId(studentId);
    }

    @DeleteMapping(value = "/{paymentId}")
    public ResponseEntity<String> deleteById(@PathVariable Long paymentId) throws EntityNotFoundException {
        return paymentService.delete(paymentId);
    }
}

