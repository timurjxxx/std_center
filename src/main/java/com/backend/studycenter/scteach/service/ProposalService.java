package com.backend.studycenter.scteach.service;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.scteach.dto.ProposalDTO;
import com.backend.studycenter.scteach.mapper.ProposalMapper;
import com.backend.studycenter.scteach.model.Proposal;
import com.backend.studycenter.scteach.repository.ProposalRepository;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalService {
    @Autowired
    private ProposalRepository proposalRepository;

    public List<Proposal> getProposals() {
        return (List<Proposal>) proposalRepository.findAll();
    }

    public Proposal getProposalById(@NotNull Long id) throws EntityNotFoundException {
        return proposalRepository.findById(id).orElseThrow(() -> new
                EntityNotFoundException("Not found proposal with id = \"" + id));
    }

    public Proposal addProposal(@NotNull ProposalDTO proposalDTO) {
        return proposalRepository.save(ProposalMapper.INSTANCE.toModel(proposalDTO));
    }

    public Proposal deleteProposalById(@NotNull Long id) throws EntityNotFoundException {
        Proposal proposal = proposalRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found proposal with id = \"" + id));
        proposalRepository.delete(proposal);
        return proposal;
    }

    public Proposal update(@NotNull Long proposalId, ProposalDTO proposalDTO) throws EntityNotFoundException {
        return ProposalMapper.INSTANCE.toModel(proposalRepository.
                findById(proposalId).orElseThrow(() ->
                        new EntityNotFoundException("Not found proposal with id = \"" + proposalId)), proposalDTO);
    }

    public void clear() {
        proposalRepository.deleteAll();
    }
}
