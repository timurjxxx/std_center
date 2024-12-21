package com.backend.studycenter.scteach.mapper;

import com.backend.studycenter.scteach.dto.ProposalDTO;
import com.backend.studycenter.scteach.model.Proposal;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProposalMapper {
    ProposalMapper INSTANCE = Mappers.getMapper(ProposalMapper.class);

    //    @Mapping(source = "proposal", target = "proposalDTO")
    Proposal toModel(ProposalDTO proposalDTO);

    //    @Mapping(source = "proposalDTO", target = "proposal")
    ProposalDTO toDTO(Proposal attendance);

    Proposal toModel(@MappingTarget Proposal proposal, ProposalDTO proposalDTO);

    static List<ProposalDTO> toDTOs(@NotNull List<Proposal> proposals) {
        return proposals.stream().map((proposal -> new ProposalDTO(proposal.getId(), proposal.getFromPersonID(),
                proposal.getToPersonsID(), proposal.getRequestType(), proposal.getDescription()))).collect(Collectors.toList());
    }
}
