package com.backend.studycenter.scteach.controller;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.scteach.dto.HomeworkDTO;
import com.backend.studycenter.scteach.mapper.HomeworkMapper;
import com.backend.studycenter.scteach.service.HomeworkService;
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
public class HomeworkController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private HomeworkService homeworkService;

    @Operation(summary = "Get a homework")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the homework",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "api/v1/homework/{id}")
    public ResponseEntity<HomeworkDTO> getHomework(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(HomeworkMapper.INSTANCE.toDTO(homeworkService.getHomeworkById(id)));
    }

    @Operation(summary = "Get a homework")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the homeworks",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "api/v1/homework")
    public ResponseEntity<List<HomeworkDTO>> getHomeworks() {
        return ResponseEntity.ok(homeworkService.getHomeworks().stream().map(homework ->
                new HomeworkDTO(homework.getId(),
                        homework.getStudentId(), homework.getAssessmentID(), homework.getFile())).collect(Collectors.toList()));
    }

    @Operation(summary = "Adding a homework")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added the homework",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
//    
    @PostMapping(value = "/api/v1/homework", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HomeworkDTO> addHomework(@RequestBody HomeworkDTO homeworkDTO) {
        try {
            return ResponseEntity.ok(HomeworkMapper.INSTANCE.toDTO(homeworkService.addHomework(homeworkDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update a homework")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the homework",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
//    
    @PutMapping(value = "/api/v1/homework/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HomeworkDTO> update(@PathVariable Long id, @RequestBody HomeworkDTO homeworkDTO) {
        try {
            return ResponseEntity.ok(HomeworkMapper.INSTANCE.toDTO(homeworkService.update(id, homeworkDTO)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a homework")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the homework",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
//    
    @DeleteMapping(value = "/api/v1/homework/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HomeworkDTO> deleteHomeworkById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(HomeworkMapper.INSTANCE.toDTO(homeworkService.deleteHomeworkById(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a all homework")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete all the homework",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = HomeworkDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
//    
    @DeleteMapping(value = "/api/v1/homework/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAll() {
        homeworkService.clear();
    }
}
