package com.backend.studycenter.scteach.mapper;

import com.backend.studycenter.scteach.dto.RespondDTO;
import com.backend.studycenter.scteach.model.Respond;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;


@Mapper
public interface RespondMapper {
    RespondMapper INSTANCE = Mappers.getMapper(RespondMapper.class);
    Respond toModel(RespondDTO respondDTO);
    RespondDTO toDTO(Respond respond);
    Respond toModel(@MappingTarget Respond respond, RespondDTO respondDTO);
    static List<RespondDTO> toDTO(@NotNull List<Respond> responds){
        return responds.stream().map((respond->new RespondDTO(respond.getId(),respond.getPersonId(),respond.getProposalId(),
                respond.getRespondStatus(),respond.getTime(),respond.getDescription()))).collect(Collectors.toList());
    }



}
