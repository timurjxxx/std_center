package com.backend.studycenter.scpay.mapper;

import com.backend.studycenter.scpay.dto.ContractDTO;
import com.backend.studycenter.scpay.model.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContractMapper {

    ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

 //   @Mapping(source = "contract",target = "contractDTO")
    ContractDTO toDTO(Contract contract);

  //  @Mapping(source = "contractDTO",target = "contract")
    Contract toModel(ContractDTO contractDTO);
}
