package com.backend.studycenter.sccontrol.controller;

import com.backend.studycenter.sccontrol.service.InterviewResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.sccontrol.dto.InterviewResultDTO;
import com.backend.studycenter.sccontrol.mapper.InterviewResultMapper;
import com.backend.studycenter.sccontrol.model.InterviewResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/interview/result")
public class InterviewResultController {
    private final Logger logger = LoggerFactory.getLogger(InterviewResultController.class);
    @Autowired
    InterviewResultService interviewResultService;

    @PreAuthorize("hasAuthority('EDITOR')")
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InterviewResultDTO> addInterviewResult(@RequestBody InterviewResultDTO interviewResultDTO) {
        try {
            return ResponseEntity.ok(InterviewResultMapper.INSTANCE.toDTO(interviewResultService.addInterviewResult(interviewResultDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @GetMapping("/getAll")
    public ResponseEntity<ArrayList<InterviewResultDTO>> getAll() {
        ArrayList<InterviewResult> interviewResults = interviewResultService.getInterviewResults();
        ArrayList<InterviewResultDTO> interviewResultDTOS = new ArrayList<>();
        for (InterviewResult interviewResult : interviewResults) {
            interviewResultDTOS.add(InterviewResultMapper.INSTANCE.toDTO(interviewResult));
        }
        if (interviewResultDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(interviewResultDTOS);
        }
    }

    @PreAuthorize("hasAuthority('VIEWER')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<InterviewResultDTO> getById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        InterviewResultDTO interviewResultDTO = InterviewResultMapper.INSTANCE.toDTO(interviewResultService.getInterviewResultById(id));
        if (interviewResultDTO == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(interviewResultDTO);
        }
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        try {
            interviewResultService.deleteInterviewResult(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Error in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAuthority('EDITOR')")
    @PutMapping("/update/{id}")
    public ResponseEntity<InterviewResultDTO> updateInterviewById(@RequestBody InterviewResultDTO interviewResultDTO, @PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(InterviewResultMapper.INSTANCE.toDTO(interviewResultService.update(interviewResultDTO, id)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
