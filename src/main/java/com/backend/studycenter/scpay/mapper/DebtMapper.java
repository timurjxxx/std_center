package com.backend.studycenter.scpay.mapper;

import com.backend.studycenter.scpay.dto.DebtDTO;
import com.backend.studycenter.scpay.model.Debt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DebtMapper {

    DebtMapper INSTANCE = Mappers.getMapper(DebtMapper.class);

  //  @Mapping(source = "debt",target = "debtDTO")
    DebtDTO toDTO(Debt debt);

   // @Mapping(source = "debtDTO",target = "debt")
    Debt toModel(DebtDTO debtDTO);
}
