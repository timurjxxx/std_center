package com.backend.studycenter.common.controller.course;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.dto.course.LessonDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.course.LessonMapper;
import com.backend.studycenter.common.model.course.Lesson;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.common.service.course.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.ArrayList;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "Authorization")
public class LessonController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private LessonService lessonService;

    @Operation(summary = "Get all lessons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the lessons",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LessonDTO.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Lesson not found",
                    content = @Content)})
    @GetMapping(value = "/api/v1/lessons")
    public ResponseEntity<ArrayList<LessonDTO>> getLessons() {
        ArrayList<Lesson> lessons = lessonService.getLessons();
        ArrayList<LessonDTO> lessonDTOs = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonDTOs.add(LessonMapper.toDTO(lesson));
        }
        if (lessonDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(lessonDTOs);
        }
    }

    @Operation(summary = "Get a lesson by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the lesson",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LessonDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Lesson not found",
                    content = @Content)})
    
    @GetMapping(value = "/api/v1/lessons/{id}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable(name = "id") Long lessonId) throws EntityNotFoundException {
        LessonDTO lessonDTO = LessonMapper.toDTO(lessonService.getLessonById(lessonId));
        if (lessonDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(lessonDTO);
        }
    }

    @Operation(summary = "Adding a lesson")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added the lesson",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LessonDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Lesson not found",
                    content = @Content)})
    
    @PostMapping(value = "/api/v1/lessons", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LessonDTO> addLesson(@RequestBody LessonDTO lessonDTO) {
        try {
            return ResponseEntity.ok(LessonMapper.toDTO(lessonService.addLesson(lessonDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Editing a lesson by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edited the lesson",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LessonDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Lesson not found",
                    content = @Content)})
    
    @PutMapping(value = "/api/v1/lessons/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LessonDTO> updateLesson(@RequestBody LessonDTO lessonDTO, @PathVariable(name = "id") Long lessonId) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(LessonMapper.toDTO(lessonService.updateLesson(lessonDTO, lessonId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Deleting a lesson by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted a lesson",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LessonDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Lesson not found",
                    content = @Content)})
    
    @DeleteMapping(value = "/api/v1/lessons/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable(name = "id") Long lessonId) throws EntityNotFoundException {
        try {
            lessonService.deleteLesson(lessonId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
