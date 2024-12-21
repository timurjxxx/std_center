package com.backend.studycenter.sccontrol.service;

import org.springframework.stereotype.Service;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sccontrol.dto.InterviewResultDTO;
import com.backend.studycenter.sccontrol.mapper.InterviewResultMapper;
import com.backend.studycenter.sccontrol.model.InterviewResult;
import com.backend.studycenter.sccontrol.repository.InterviewResultRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;


@Service
public class InterviewResultService {

    @Autowired
    InterviewResultRepository interviewResultRepository;

    public ArrayList<InterviewResult> getInterviewResults() {
        return (ArrayList<InterviewResult>) interviewResultRepository.findAll();
    }

    public InterviewResult getInterviewResultById(Long id) throws EntityNotFoundException {
        InterviewResult interviewResult = interviewResultRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found interview result by given id: " + id));
        return interviewResult;
    }

    public InterviewResult addInterviewResult(InterviewResultDTO interviewResultDTO) {
        return interviewResultRepository.save(InterviewResultMapper.INSTANCE.toModel(interviewResultDTO));
    }

    public InterviewResult update(InterviewResultDTO interviewResultDTO, Long id) throws EntityNotFoundException {
        InterviewResult existInterviewResult = interviewResultRepository.findInterviewResultById(id);
        if (existInterviewResult == null) {
            throw new EntityNotFoundException("Interview Result not found");
        }
        InterviewResult updateInterviewResult = InterviewResultMapper.INSTANCE.toModel(interviewResultDTO);
        updateInterviewResult.setId(existInterviewResult.getId());
        interviewResultRepository.save(updateInterviewResult);
        return updateInterviewResult;
    }

    public void deleteInterviewResult(Long id) throws EntityNotFoundException {
        InterviewResult interviewResult = interviewResultRepository.findInterviewResultById(id);
        if (interviewResult == null) {
            throw new EntityNotFoundException("Not found");
        }
        interviewResultRepository.delete(interviewResult);
    }

    public void clear() {
        interviewResultRepository.deleteAll();
    }
}
