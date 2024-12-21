package com.backend.studycenter.scpay.controller;

import static com.backend.studycenter.scpay.constant.AppConstants.API_VERSION;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.scpay.dto.request.ContractRequestDTO;
import com.backend.studycenter.scpay.dto.request.ContractTeacherRequestDTO;
import com.backend.studycenter.scpay.dto.response.ContractResponseDTO;
import com.backend.studycenter.scpay.dto.response.ContractTeacherResponseDTO;
import com.backend.studycenter.scpay.service.ContractService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = API_VERSION + "/contracts")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ContractResponseDTO> create(@RequestBody ContractRequestDTO requestDTO) {
        try {
            return contractService.create(requestDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ContractResponseDTO> update(@RequestBody ContractRequestDTO requestDTO) throws EntityNotFoundException {
        return contractService.update(requestDTO);
    }

    @GetMapping("/{studentId}/{groupId}")
    public ResponseEntity<ContractResponseDTO> getByStudentGroupId(@PathVariable Long studentId, @PathVariable Long groupId) throws EntityNotFoundException {
        return contractService.getByStudentGroupId(groupId, studentId);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<List<ContractResponseDTO>> getByStudentId(@PathVariable Long studentId) {
        return contractService.allByStudentId(studentId);
    }

    @DeleteMapping("/{studentId}/{groupId}")
    public ResponseEntity<String> deleteByStudentGroupId(@PathVariable Long studentId, @PathVariable Long groupId) throws EntityNotFoundException {
        return contractService.deleteByStudentGroupId(studentId, groupId);
    }

    //

    @PostMapping(value = "createTeacherContract",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ContractTeacherResponseDTO> createTeacherContract(@RequestBody ContractTeacherRequestDTO requestDTO) {
        try {
            return contractService.createTeacherContract(requestDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "updateTeacherContract",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ContractTeacherResponseDTO> updateTeacherContract(@RequestBody ContractTeacherRequestDTO requestDTO) throws EntityNotFoundException {
        return contractService.updateTeacherContract(requestDTO);
    }

    @GetMapping("getTeacherContract/{teacherId}")
    public ResponseEntity<ContractTeacherResponseDTO> getByTeacherId(@PathVariable Long teacherId) throws EntityNotFoundException {
        return contractService.getByTeacherId(teacherId);
    }

    @DeleteMapping("deleteTeacherContract/{teacherId}")
    public ResponseEntity<String> deleteByTeacherId(@PathVariable Long teacherId) throws EntityNotFoundException {
        return contractService.deleteContractByTeacherId(teacherId);
    }

}