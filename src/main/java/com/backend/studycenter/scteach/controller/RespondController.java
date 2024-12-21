package com.backend.studycenter.scteach.controller;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.scteach.dto.RespondDTO;
import com.backend.studycenter.scteach.mapper.RespondMapper;
import com.backend.studycenter.scteach.service.RespondService;
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
public class RespondController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private RespondService respondService;

    @Operation(summary = "Get a respond")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the respond",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespondDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespondDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "api/v1/respond/{id}")
    public ResponseEntity<RespondDTO> getRespond(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(RespondMapper.INSTANCE.toDTO(respondService.getRespondById(id)));
    }

    @Operation(summary = "Get a respond")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the respond",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespondDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespondDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "api/v1/respond")
    public ResponseEntity<List<RespondDTO>> getResponds() {
        return ResponseEntity.ok(respondService.getResponds().stream().map(respond ->
                new RespondDTO(respond.getId(), respond.getPersonId(), respond.getProposalId(), respond.getRespondStatus(),
                        respond.getTime(), respond.getDescription())).collect(Collectors.toList()));
    }

    @Operation(summary = "Adding a respond")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added the respond",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespondDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
//    
    @PostMapping(value = "/api/v1/respond", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespondDTO> addRespond(@RequestBody RespondDTO respondDTO) {
        try {
            return ResponseEntity.ok(RespondMapper.INSTANCE.toDTO(respondService.addRespond(respondDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update a respond")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the proposal",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespondDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
//    
    @PutMapping(value = "/api/v1/respond/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespondDTO> update(@PathVariable Long id, @RequestBody RespondDTO respondDTO) {
        try {
            return ResponseEntity.ok(RespondMapper.INSTANCE.toDTO(respondService.update(id, respondDTO)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a respond")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the respond",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespondDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
//    
    @DeleteMapping(value = "/api/v1/respond/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespondDTO> deleteRespondById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(RespondMapper.INSTANCE.toDTO(respondService.deleteRespondById(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a all respond")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete all the respond",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = RespondDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
//    
    @DeleteMapping(value = "/api/v1/respond/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAll() {
        respondService.clear();
    }
}
