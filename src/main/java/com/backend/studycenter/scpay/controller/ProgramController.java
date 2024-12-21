package com.backend.studycenter.scpay.controller;

import java.util.List;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import static com.backend.studycenter.scpay.constant.AppConstants.API_VERSION;
import com.backend.studycenter.scpay.dto.request.ProgramRequestDTO;
import com.backend.studycenter.scpay.dto.response.ProgramResponseDTO;
import com.backend.studycenter.scpay.service.ProgramService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(value = API_VERSION + "/programs")
@RequiredArgsConstructor
public class ProgramController {
    private final ProgramService programService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProgramResponseDTO> create(@RequestBody ProgramRequestDTO requestDTO) {
        return programService.create(requestDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProgramResponseDTO> getById(@PathVariable Long id) throws EntityNotFoundException {
        return programService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<ProgramResponseDTO>> getAll() throws EntityNotFoundException {
        return programService.getAll();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProgramResponseDTO> update(@RequestBody ProgramRequestDTO requestDTO, @PathVariable Long id) throws EntityNotFoundException {
        return programService.update(requestDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws EntityNotFoundException {
        return programService.deleteById(id);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        return programService.deleteAll();
    }
}
