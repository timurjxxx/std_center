package com.backend.studycenter.scpay.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.backend.studycenter.scpay.dto.request.SubcontractRequestDTO;
import com.backend.studycenter.scpay.dto.response.SubcontractResponseDTO;
import com.backend.studycenter.scpay.model.Contract;
import com.backend.studycenter.scpay.model.Subcontract;

public interface SubcontractMapper {
    static List<Subcontract> requestToModel(List<SubcontractRequestDTO> addingSubcontracts, Contract contract) {
        List<Subcontract> subcontracts = new ArrayList<>();
        Subcontract subcontract;
        for (SubcontractRequestDTO addingSubcontract : addingSubcontracts) {
            subcontract = Subcontract.builder()
                    .amountEachMonth(new BigDecimal(addingSubcontract.getAmountEachMonth()))
                    .dateOfMonth(addingSubcontract.getDateOfMonth())
                    .isActive(true)
                    .contract(contract).build();
            subcontracts.add(subcontract);
        }
        return subcontracts;
    }

    static List<SubcontractResponseDTO> modelToResponseAll(List<Subcontract> subcontracts) {
        List<SubcontractResponseDTO> responseDTOS = new ArrayList<>();
        SubcontractResponseDTO responseDTO;
        for (Subcontract subcontract : subcontracts) {
            responseDTO = SubcontractResponseDTO.builder()
                    .amountEachMonth(subcontract.getAmountEachMonth())
                    .dateOfMonth(subcontract.getDateOfMonth())
                    .build();
            responseDTOS.add(responseDTO);
        }
        return responseDTOS;
    }
}