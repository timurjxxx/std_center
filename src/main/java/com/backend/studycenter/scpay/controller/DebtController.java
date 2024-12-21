package com.backend.studycenter.scpay.controller;

import static com.backend.studycenter.scpay.constant.AppConstants.API_VERSION;
import com.backend.studycenter.scpay.dto.response.DebtResponseDTO;
import com.backend.studycenter.scpay.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = API_VERSION + "/debts")
@RequiredArgsConstructor
public class DebtController {
    private final DebtService debtService;

    @GetMapping("/{studentId}")
    public ResponseEntity<DebtResponseDTO> getByStudentId(@PathVariable Long studentId) {
        return debtService.getByStudentId(studentId);
    }

    @GetMapping("/{studentId}/{groupId}")
    public ResponseEntity<DebtResponseDTO> getByStudentAndGroupId(@PathVariable Long studentId, @PathVariable Long groupId) {
        return debtService.getByStudentGroupId(studentId, groupId);
    }
}