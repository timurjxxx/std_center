package com.backend.studycenter.scpay.controller;

import java.util.List;


import com.backend.studycenter.common.exception.EntityNotFoundException;
import static com.backend.studycenter.scpay.constant.AppConstants.API_VERSION;
import com.backend.studycenter.scpay.dto.request.DepartmentRequestDTO;
import com.backend.studycenter.scpay.dto.response.DepartmentResponseDTO;
import com.backend.studycenter.scpay.service.DepartmentService;
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
@RequestMapping(value = API_VERSION + "/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DepartmentResponseDTO> create(@RequestBody DepartmentRequestDTO dto) {
        return departmentService.create(dto);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DepartmentResponseDTO> update(@RequestBody DepartmentRequestDTO dto, @PathVariable Long id) throws EntityNotFoundException {
        return departmentService.update(dto, id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<DepartmentResponseDTO>> getAll() {
        return departmentService.list();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) throws EntityNotFoundException {
        return departmentService.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DepartmentResponseDTO> getOneById(@PathVariable Long id) throws EntityNotFoundException {
        return departmentService.getOneById(id);
    }
}