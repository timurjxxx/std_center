package com.backend.studycenter.common.service.person;

import com.backend.studycenter.common.dto.person.InterviewDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.person.InterviewMapper;
import com.backend.studycenter.common.model.person.Interview;
import com.backend.studycenter.common.repository.person.InterviewRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;

    public ArrayList<Interview> getInterviews() {
        return (ArrayList<Interview>) interviewRepository.findAll();
    }

    public Interview getInterviewById(Long interviewId) throws EntityNotFoundException {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new EntityNotFoundException("Not found interview by id = " + interviewId));
        return interview;
    }

    public Interview addInterview(InterviewDTO interviewDTO) {
        return interviewRepository.save(InterviewMapper.INSTANCE.toModel(interviewDTO));
    }

    public void deleteInterview(Long interviewId) throws EntityNotFoundException {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new EntityNotFoundException("Not found interview by id = " + interviewId));
        interviewRepository.delete(interview);
    }

    public Interview updateInterview(InterviewDTO interviewDTO, Long interviewId) throws EntityNotFoundException {
        Interview existingInterview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new EntityNotFoundException("Not found interview by id = " + interviewId));
        Interview updatedInterview = InterviewMapper.INSTANCE.toModel(interviewDTO);
        updatedInterview.setId(existingInterview.getId());
        return interviewRepository.save(updatedInterview);
    }

    public void clear() {
        interviewRepository.deleteAll();
    }
}
