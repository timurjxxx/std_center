package com.backend.studycenter.scpay.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.scpay.dto.request.ProgramRequestDTO;
import com.backend.studycenter.scpay.dto.response.ProgramResponseDTO;
import com.backend.studycenter.scpay.model.Program;
import com.backend.studycenter.scpay.repository.ProgramRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {
    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public ResponseEntity<ProgramResponseDTO> create(ProgramRequestDTO requestDTO) {
        String name = requestDTO.getName();
        Optional<Program> programOptional = programRepository.findByName(name);
        if (programOptional.isPresent()) {
            Program program = programOptional.get();
            if (program.getIsActive())
                throw new RuntimeException("program with %s name already exists".formatted(name));
        }
        Program program = Program.builder()
                .name(name)
                .description(requestDTO.getDescription())
                .createdAt(LocalDateTime.now())
                .isActive(true).build();

        Program savedProgram = programRepository.save(program);
        ProgramResponseDTO responseDTO = toResponseProgramDTO(savedProgram);
        return ResponseEntity.ok(responseDTO);
    }


    public ResponseEntity<ProgramResponseDTO> findById(Long id) throws EntityNotFoundException {
        Optional<Program> byId = programRepository.findById(id);
        if (byId.isEmpty())
            throw new EntityNotFoundException("program with %s id not found ".formatted(id));

        return ResponseEntity.ok(toResponseProgramDTO(byId.get()));
    }

    public ResponseEntity<List<ProgramResponseDTO>> getAll() throws EntityNotFoundException {
        List<Program> all = programRepository.findAllActive();
        if (all.isEmpty())
            throw new EntityNotFoundException("no program available yet");

        List<ProgramResponseDTO> responseDTOS = new ArrayList<>();
        for (Program program : all) {
            responseDTOS.add(toResponseProgramDTO(program));
        }
        return ResponseEntity.ok(responseDTOS);
    }

    public ResponseEntity<ProgramResponseDTO> update(ProgramRequestDTO requestDTO, Long id) throws EntityNotFoundException {
        Optional<Program> byId = programRepository.findById(id);
        if (byId.isEmpty())
            throw new EntityNotFoundException("program with %s id not found ".formatted(id));
        Program program = byId.get();
        if (!program.getIsActive())
            throw new EntityNotFoundException("program with %s id not found ".formatted(id));

        program.setName(requestDTO.getName());
        program.setDescription(requestDTO.getDescription());
        Program savedProgram = programRepository.save(program);

        return ResponseEntity.ok(toResponseProgramDTO(savedProgram));
    }

    public ResponseEntity<String> deleteById(Long id) throws EntityNotFoundException {
        Optional<Program> byId = programRepository.findById(id);
        if (byId.isEmpty())
            throw new EntityNotFoundException("proram with %s id not found ".formatted(id));
        Program program = byId.get();
        if (!program.getIsActive())
            throw new EntityNotFoundException("proram with %s id not found ".formatted(id));
        program.setIsActive(false);
        programRepository.save(program);
        return ResponseEntity.ok("program with %s id successfully deletd ".formatted(id));
    }

    public ResponseEntity<String> deleteAll() {
        List<Program> allActive = programRepository.findAllActive();
        for (Program program : allActive) {
            program.setIsActive(false);
            programRepository.save(program);
        }
        return ResponseEntity.ok("programs successfully deleletd");
    }

    private ProgramResponseDTO toResponseProgramDTO(Program savedProgram) {
        return ProgramResponseDTO.builder()
                .id(savedProgram.getId())
                .name(savedProgram.getName())
                .description(savedProgram.getDescription())
                .createdAt(savedProgram.getCreatedAt())
                .updatedAt(savedProgram.getUpdatedAt()).build();
    }
}