package com.backend.studycenter.scteach.controller;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.scteach.dto.AttendanceDTO;
import com.backend.studycenter.scteach.mapper.AttendanceMapper;
import com.backend.studycenter.scteach.service.AttendanceService;
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
public class AttendanceController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private AttendanceService attendanceService;

    @Operation(summary = "Get a attendance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the attendance",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "api/v1/attendance/{id}")
    public ResponseEntity<AttendanceDTO> getAttendance(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(AttendanceMapper.INSTANCE.toDTO(attendanceService.getAttendanceById(id)));
    }

    @Operation(summary = "Get a attendance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the attendances",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "api/v1/attendance")
    public ResponseEntity<List<AttendanceDTO>> getAttendances() {
        return ResponseEntity.ok(attendanceService.getAttendances().stream().map(attendance ->
                new AttendanceDTO(attendance.getId(),
                        attendance.getGroupID(), attendance.getCalendarLessonID())).collect(Collectors.toList()));
    }

    @Operation(summary = "Adding a attendance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added the attendance",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
//    
    @PostMapping(value = "/api/v1/attendance", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AttendanceDTO> addAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        try {
            return ResponseEntity.ok(AttendanceMapper.INSTANCE.toDTO(attendanceService.addAttendance(attendanceDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update a attendance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the attendance",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
//    
    @PutMapping(value = "/api/v1/attendance/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AttendanceDTO> update(@PathVariable Long id, @RequestBody AttendanceDTO attendanceDTO) {
        try {
            return ResponseEntity.ok(AttendanceMapper.INSTANCE.toDTO(attendanceService.update(id, attendanceDTO)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a attendance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the attendance",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
//    
    @DeleteMapping(value = "/api/v1/attendance/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AttendanceDTO> deleteAttendanceById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(AttendanceMapper.INSTANCE.toDTO(attendanceService.deleteAttendanceById(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a all attendance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete all the attendance",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttendanceDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
//    
    @DeleteMapping(value = "/api/v1/attendance/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAll() {
        attendanceService.clear();
    }
}
