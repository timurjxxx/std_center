package com.backend.studycenter.scteach.controller;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.scteach.dto.ProposalDTO;
import com.backend.studycenter.scteach.mapper.ProposalMapper;
import com.backend.studycenter.scteach.service.ProposalService;
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
public class ProposalController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private ProposalService proposalService;

    @Operation(summary = "Get a proposal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the proposal",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "api/v1/proposal/{id}")
    public ResponseEntity<ProposalDTO> getProposal(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(ProposalMapper.INSTANCE.toDTO(proposalService.getProposalById(id)));
    }

    @Operation(summary = "Get a proposal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the proposals",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
    @GetMapping(value = "api/v1/proposal")
    public ResponseEntity<List<ProposalDTO>> getProposals() {
        return ResponseEntity.ok(proposalService.getProposals().stream().map(proposal ->
                new ProposalDTO(proposal.getId(),
                        proposal.getFromPersonID(), proposal.getToPersonsID(), proposal.getRequestType(),
                        proposal.getDescription())).collect(Collectors.toList()));
    }

    @Operation(summary = "Adding a proposal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added the proposal",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
//    
    @PostMapping(value = "/api/v1/proposal", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProposalDTO> addProposal(@RequestBody ProposalDTO proposalDTO) {
        try {
            return ResponseEntity.ok(ProposalMapper.INSTANCE.toDTO(proposalService.addProposal(proposalDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Update a proposal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update the proposal",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
//    
    @PutMapping(value = "/api/v1/proposal/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProposalDTO> update(@PathVariable Long id, @RequestBody ProposalDTO proposalDTO) {
        try {
            return ResponseEntity.ok(ProposalMapper.INSTANCE.toDTO(proposalService.update(id, proposalDTO)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a proposal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the proposal",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content)})
//    
    @DeleteMapping(value = "/api/v1/proposal/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProposalDTO> deleteProposalById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ProposalMapper.INSTANCE.toDTO(proposalService.deleteProposalById(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a all proposal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete all the proposal",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProposalDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content)})
//    
    @DeleteMapping(value = "/api/v1/proposal/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAll() {
        proposalService.clear();
    }
}
