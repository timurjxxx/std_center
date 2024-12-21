package com.backend.studycenter.scpay.service;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.scpay.dto.request.DepartmentRequestDTO;
import com.backend.studycenter.scpay.dto.response.DepartmentResponseDTO;
import com.backend.studycenter.scpay.model.Department;
import com.backend.studycenter.scpay.repository.DepartmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public ResponseEntity<DepartmentResponseDTO> create(DepartmentRequestDTO requestDTO) {
        String name = requestDTO.getName();
        Optional<Department> departmentOptional = departmentRepository.findByName(name);
        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            if (department.getIsActive()) {
                throw new RuntimeException("Department already exists by name %s".formatted(name));
            }
        }
        Department department = Department.builder()
                .name(name)
                .description(requestDTO.getDescription())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        Department savedDepartment = departmentRepository.save(department);
        DepartmentResponseDTO responseDTO = mapDepartmentResponseDTO(savedDepartment);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<List<DepartmentResponseDTO>> list() {
        List<Department> departmentList = departmentRepository.findAllIsActive();
        List<DepartmentResponseDTO> responseDTOList = departmentList.stream()
                .map(DepartmentService::mapDepartmentResponseDTO)
                .toList();
        return ResponseEntity.ok(responseDTOList);
    }

    public ResponseEntity<DepartmentResponseDTO> update(DepartmentRequestDTO requestDTO, Long departmentId) throws EntityNotFoundException {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isEmpty()) {
            throw new EntityNotFoundException("Department not found by given id : %s".formatted(departmentId));
        }
        Department department = optionalDepartment.get();
        String name = requestDTO.getName();
        Optional<Department> departmentOptional = departmentRepository.findByName(name);
        if (departmentOptional.isPresent()) {
            Department departmentByName = departmentOptional.get();
            if (departmentByName.getIsActive() && !department.getId().equals(departmentByName.getId())) {
                throw new RuntimeException("Department already exists by name %s".formatted(name));
            }
        }
        department.setName(name);
        department.setDescription(requestDTO.getDescription());
        department.setIsActive(true);
        department.setUpdatedAt(LocalDateTime.now());
        Department savedDepartment = departmentRepository.save(department);
        DepartmentResponseDTO responseDTO = mapDepartmentResponseDTO(savedDepartment);
        return ResponseEntity.ok(responseDTO);
    }

    public static DepartmentResponseDTO mapDepartmentResponseDTO(Department department) {
        return DepartmentResponseDTO.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .createdAt(department.getCreatedAt())
                .updatedAt(department.getUpdatedAt())
                .build();
    }

    public ResponseEntity<String> deleteById(Long departmentId) throws EntityNotFoundException {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isEmpty()) {
            throw new EntityNotFoundException("Department not found by giving id : %s".formatted(departmentId));
        }
        Department department = departmentOptional.get();
        department.setIsActive(false);
        departmentRepository.save(department);
        return ResponseEntity.ok("Successfully deleted");
    }

    public ResponseEntity<DepartmentResponseDTO> getOneById(Long departmentId) throws EntityNotFoundException {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isEmpty()) {
            throw new EntityNotFoundException("Department not found by giving id : %s".formatted(departmentId));
        }
        Department department = departmentOptional.get();
        if (!department.getIsActive()) {
            throw new EntityNotFoundException("Department not found by giving id : %s".formatted(departmentId));
        }
        DepartmentResponseDTO responseDTO = mapDepartmentResponseDTO(department);
        return ResponseEntity.ok(responseDTO);
    }
}