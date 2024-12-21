package com.backend.studycenter.scteach.service;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.scteach.dto.RespondDTO;
import com.backend.studycenter.scteach.mapper.RespondMapper;
import com.backend.studycenter.scteach.model.Respond;
import com.backend.studycenter.scteach.repository.RespondRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RespondService {
    @Autowired
    private RespondRepository respondRepository;

    public List<Respond> getResponds() {
        return (List<Respond>) respondRepository.findAll();
    }

    public Respond getRespondById(@NotNull Long id) throws EntityNotFoundException {
        return respondRepository.findById(id).orElseThrow(() -> new
                EntityNotFoundException("Not found respond with id = \"" + id));
    }

    public Respond addRespond(@NotNull RespondDTO respondDTO) {
        return respondRepository.save(RespondMapper.INSTANCE.toModel(respondDTO));
    }

    public Respond deleteRespondById(@NotNull Long id) throws EntityNotFoundException {
        Respond proposal = respondRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found proposal with id = \"" + id));
        respondRepository.delete(proposal);
        return proposal;
    }

    public Respond update(@NotNull Long respondId, RespondDTO respondDTO) throws EntityNotFoundException {
        return RespondMapper.INSTANCE.toModel(respondRepository.
                findById(respondId).orElseThrow(() ->
                        new EntityNotFoundException("Not found proposal with id = \"" + respondId)), respondDTO);
    }

    public void clear() {
        respondRepository.deleteAll();
    }
}
