package com.backend.studycenter.scpay.service;

import java.util.List;

import com.backend.studycenter.scpay.dto.response.SubcontractResponseDTO;
import com.backend.studycenter.scpay.mapper.SubcontractMapper;
import com.backend.studycenter.scpay.model.Subcontract;
import com.backend.studycenter.scpay.repository.SubcontractRepository;
import org.springframework.stereotype.Service;

@Service
public class SubcontractService {
    private final SubcontractRepository subcontractRepository;

    public SubcontractService(SubcontractRepository subcontractRepository) {
        this.subcontractRepository = subcontractRepository;
    }

    public List<SubcontractResponseDTO> getByStudentId(Long studentId) {
        List<Subcontract> subcontracts = subcontractRepository.findAllByStudentId(studentId);
        if (subcontracts.isEmpty())
            throw new RuntimeException("subcontract with student id %s not found ".formatted(studentId));
        return SubcontractMapper.modelToResponseAll(subcontracts);
    }

    public List<SubcontractResponseDTO> getResponseByStudentGroupId(Long studentId, Long groupId) {
        List<Subcontract> subcontracts = subcontractRepository.findAllBystudentGroupId(studentId, groupId);
        if (subcontracts.isEmpty())
            throw new RuntimeException("any subcontract with student id %s and group id %s not found ".formatted(studentId, groupId));

        return SubcontractMapper.modelToResponseAll(subcontracts);
    }
}