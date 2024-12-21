package com.backend.studycenter.scpay.mapper;

import com.backend.studycenter.scpay.dto.ProgramDTO;
import com.backend.studycenter.scpay.model.Program;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProgramMapper{

    ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);

  //  @Mapping(source = "program",target = "programDTO")
    ProgramDTO toDTO(Program program);

 //   @Mapping(source = "programDTO",target = "program")
    Program toModel(ProgramDTO programDTO);
}
