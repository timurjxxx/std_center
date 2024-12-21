package com.backend.studycenter.scteach.controller;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.scteach.dto.AssessmentDTO;
import com.backend.studycenter.scteach.mapper.AssessmentMapper;
import com.backend.studycenter.scteach.service.AssessmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.stream.Collectors;
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
public class AssessmentController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private AssessmentService assessmentService;

    @Operation(summary = "Get a assessment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the assessment",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AssessmentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AssessmentDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "api/v1/assessment/{id}")
    public ResponseEntity<AssessmentDTO> getAssessment(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(AssessmentMapper.INSTANCE.toDTO(assessmentService.getAssessmentById(id)));
    }

    @Operation(summary = "Get a assessment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the assessments",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AssessmentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AssessmentDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "api/v1/assessment")
    public ResponseEntity<List<AssessmentDTO>> getAssessments() {
        return ResponseEntity.ok(assessmentService.getAssessments().stream().map(assessment ->
                new AssessmentDTO(assessment.getId(),
                        assessment.getTeacherID(), assessment.getDescription(),
                        assessment.getGroupID())).collect(Collectors.toList()));
    }

    @Operation(summary = "Adding a assessment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added the assessment",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AssessmentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
//    
    @PostMapping(value = "/api/v1/assessment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AssessmentDTO> addAssessment(@RequestBody AssessmentDTO assessmentDTO) {
        try {
            return ResponseEntity.ok(AssessmentMapper.INSTANCE.toDTO(assessmentService.addAssessment(assessmentDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update a assessment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the assessment",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AssessmentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
//    
    @PutMapping(value = "/api/v1/assessment/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AssessmentDTO> update(@PathVariable Long id, @RequestBody AssessmentDTO assessmentDTO) {
        try {
            return ResponseEntity.ok(AssessmentMapper.INSTANCE.toDTO(assessmentService.updateAssessment(id, assessmentDTO)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a assessment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the assessment",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AssessmentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
//    
    @DeleteMapping(value = "/api/v1/assessment/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AssessmentDTO> deleteAssessmentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(AssessmentMapper.INSTANCE.toDTO(assessmentService.delete(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a all assessment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete all the assessment",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AssessmentDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
//    
    @DeleteMapping(value = "/api/v1/assessment/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAll() {
        assessmentService.clear();
    }
}