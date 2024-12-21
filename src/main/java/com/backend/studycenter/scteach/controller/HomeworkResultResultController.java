package com.backend.studycenter.scteach.controller;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.scteach.dto.HomeworkResultDTO;
import com.backend.studycenter.scteach.mapper.HomeworkResultMapper;
import com.backend.studycenter.scteach.service.HomeworkResultService;
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
public class HomeworkResultResultController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private HomeworkResultService homeworkResultService;

    @Operation(summary = "Get a homeworkResult")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the homeworkResult",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkResultDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkResultDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "api/v1/homeworkResult/{id}")
    public ResponseEntity<HomeworkResultDTO> getHomeworkResult(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(HomeworkResultMapper.INSTANCE.toDTO(homeworkResultService.getHomeworkResultById(id)));
    }

    @Operation(summary = "Get a homeworkResult")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the homeworkResults",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkResultDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkResultDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "api/v1/homeworkResult")
    public ResponseEntity<List<HomeworkResultDTO>> getHomeworkResults() {
        return ResponseEntity.ok(homeworkResultService.getHomeworkResults().stream().map(homeworkResult ->
                new HomeworkResultDTO(homeworkResult.getId(),
                        homeworkResult.getStudentId(), homeworkResult.getHomeworkId(), homeworkResult.getTeacherID(),
                        homeworkResult.getScore(), homeworkResult.getDescription())).collect(Collectors.toList()));
    }

    @Operation(summary = "Adding a homeworkResult")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added the homeworkResult",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkResultDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
//    
    @PostMapping(value = "/api/v1/homeworkResult", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HomeworkResultDTO> addHomeworkResult(@RequestBody HomeworkResultDTO homeworkResultDTO) {
        try {
            return ResponseEntity.ok(HomeworkResultMapper.INSTANCE.toDTO(homeworkResultService.addHomeworkResult(homeworkResultDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update a homeworkResult")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the homeworkResult",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkResultDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
//    
    @PutMapping(value = "/api/v1/homeworkResult/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HomeworkResultDTO> update(@PathVariable Long id, @RequestBody HomeworkResultDTO homeworkResultDTO) {
        try {
            return ResponseEntity.ok(HomeworkResultMapper.INSTANCE.toDTO(homeworkResultService.update(id, homeworkResultDTO)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a homeworkResult")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the homeworkResult",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkResultDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
//    
    @DeleteMapping(value = "/api/v1/homeworkResult/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HomeworkResultDTO> deleteHomeworkResultById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(HomeworkResultMapper.INSTANCE.toDTO(homeworkResultService.deleteHomeworkResultById(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a all homeworkResult")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete all the homeworkResult",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkResultDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
//    
    @DeleteMapping(value = "/api/v1/homeworkResult/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAll() {
        homeworkResultService.clear();
    }
}
