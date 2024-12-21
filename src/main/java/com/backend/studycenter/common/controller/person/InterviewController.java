package com.backend.studycenter.common.controller.person;

import com.backend.studycenter.common.dto.person.InterviewDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.person.InterviewMapper;
import com.backend.studycenter.common.model.person.Interview;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.common.service.person.InterviewService;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class InterviewController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private InterviewService interviewService;

    
    @GetMapping(value = "/api/v1/interviews")
    public ResponseEntity<ArrayList<InterviewDTO>> getInterviews() {
        ArrayList<Interview> interviews = interviewService.getInterviews();
        ArrayList<InterviewDTO> interviewDTOs = new ArrayList<>();
        for (Interview interview : interviews) {
            interviewDTOs.add(InterviewMapper.INSTANCE.toDTO(interview));
        }
        if (interviewDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(interviewDTOs);
        }
    }

    
    @GetMapping(value = "/api/v1/interviews/{id}")
    public ResponseEntity<InterviewDTO> getInterviewById(@PathVariable(name = "id") Long interviewId) throws EntityNotFoundException {
        InterviewDTO interviewDTO = InterviewMapper.INSTANCE.toDTO(interviewService.getInterviewById(interviewId));
        if (interviewDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(interviewDTO);
        }
    }

    
    @PostMapping(value = "/api/v1/interviews", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterviewDTO> addInterview(@RequestBody InterviewDTO interview) {
        try {
            return ResponseEntity.ok(InterviewMapper.INSTANCE.toDTO(interviewService.addInterview(interview)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PutMapping(value = "/api/v1/interviews/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterviewDTO> updateInterview(@RequestBody InterviewDTO interviewDTO, @PathVariable(name = "id") Long interviewId) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(InterviewMapper.INSTANCE.toDTO(interviewService.updateInterview(interviewDTO, interviewId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @DeleteMapping(value = "/api/v1/interviews/{id}")
    public ResponseEntity<Void> deleteInterview(@PathVariable(name = "id") Long interviewId) throws EntityNotFoundException {
        try {
            interviewService.deleteInterview(interviewId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
