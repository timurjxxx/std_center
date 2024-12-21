package com.backend.studycenter.scpay.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.model.education.Group;
import com.backend.studycenter.common.model.person.Student;
import com.backend.studycenter.common.model.person.Teacher;
import com.backend.studycenter.common.repository.education.GroupRepository;
import com.backend.studycenter.common.repository.person.StudentRepository;
import com.backend.studycenter.common.repository.person.TeacherRepository;
import com.backend.studycenter.scpay.dto.request.ContractRequestDTO;
import com.backend.studycenter.scpay.dto.request.ContractTeacherRequestDTO;
import com.backend.studycenter.scpay.dto.request.SubcontractRequestDTO;
import com.backend.studycenter.scpay.dto.response.ContractResponseDTO;
import com.backend.studycenter.scpay.dto.response.ContractTeacherResponseDTO;
import com.backend.studycenter.scpay.dto.response.SubcontractResponseDTO;
import com.backend.studycenter.scpay.enums.ContractStatus;
import com.backend.studycenter.scpay.mapper.SubcontractMapper;
import com.backend.studycenter.scpay.model.Contract;
import com.backend.studycenter.scpay.model.Subcontract;
import com.backend.studycenter.scpay.repository.ContractRepository;
import com.backend.studycenter.scpay.repository.SubcontractRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    private final TeacherRepository teacherRepository;

    private final SubcontractRepository subcontractRepository;

    public ContractService(ContractRepository contractRepository,
                           StudentRepository studentRepository,
                           GroupRepository groupRepository,
                           TeacherRepository teacherRepository,
                           SubcontractRepository subcontractRepository) {
        this.contractRepository = contractRepository;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.teacherRepository=teacherRepository;
        this.subcontractRepository = subcontractRepository;
    }

    public ResponseEntity<ContractResponseDTO> create(ContractRequestDTO requestDTO) throws EntityNotFoundException {

        Long studentId = requestDTO.getStudentId();
        Long groupId = requestDTO.getGroupId();
        Optional<Contract> contractOptional = contractRepository.findByStudentIdAndGroupId(studentId, groupId);

        if (contractOptional.isPresent())
            throw new RuntimeException("contract with studentId %s and groupId %s already exists ".formatted(studentId, groupId));

        Optional<Student> studentOptional = studentRepository.findById(requestDTO.getStudentId());

        if (studentOptional.isEmpty())
            throw new EntityNotFoundException("student with id %s not found ".formatted(studentId));

        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if (groupOptional.isEmpty())
            throw new EntityNotFoundException("group with id %s not found ".formatted(groupId));

        Contract contract = mapToContract(requestDTO, studentOptional.get(), groupOptional.get());
        Contract savedContract = contractRepository.save(contract);

        List<SubcontractRequestDTO> subcontractRequests = requestDTO.getSubcontractRequests();
        List<Subcontract> subcontractList = subcontractRequests.stream()
                .map(requestDTO1 -> mapToSubcontract(requestDTO1, savedContract))
                .toList();
        subcontractRepository.saveAll(subcontractList);
        List<SubcontractResponseDTO> responseDTOS = mapToSubcontractResponses(subcontractRequests);
        return ResponseEntity.ok(mapToContractResponseDTO(savedContract, responseDTOS));
    }


    public ResponseEntity<ContractResponseDTO> update(ContractRequestDTO requestDTO) throws EntityNotFoundException {
        Long studentId = requestDTO.getStudentId();
        Long groupId = requestDTO.getGroupId();

        Optional<Contract> contractOptional = contractRepository.findByStudentIdAndGroupId(studentId, groupId);
        if (contractOptional.isEmpty())
            throw new EntityNotFoundException("contract with student id %s and group id %s not found "
                    .formatted(studentId, groupId));

        Contract contract = contractOptional.get();
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty())
            throw new EntityNotFoundException("student with id %s not found ".formatted(studentId));

        Student student = studentOptional.get();

        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if (groupOptional.isEmpty())
            throw new EntityNotFoundException("group with id %s not found ".formatted(groupId));

        Group group = groupOptional.get();
        BigDecimal paymentAmount = new BigDecimal(requestDTO.getPaymentAmount());
        contract.setStudent(student);
        contract.setGroup(group);
        contract.setPaymentAmount(paymentAmount);
        contract.setDurationOfCourse(requestDTO.getDurationOfCourse());
        Contract savedContract = contractRepository.save(contract);

        List<Subcontract> existingSubcontracts = subcontractRepository.findAllByContractId(contract.getId());
        List<SubcontractRequestDTO> addingSubcontracts = requestDTO.getSubcontractRequests();

        List<Subcontract> subcontractsToAdd = addingSubcontracts.stream()
                .map(subcontractRequestDTO -> mapToSubcontract(subcontractRequestDTO, savedContract))
                .toList();
        subcontractRepository.deleteAll(existingSubcontracts);
        subcontractRepository.saveAll(subcontractsToAdd);

        List<SubcontractResponseDTO> subcontractResponseDTOS = mapToSubcontractResponses(addingSubcontracts);
        return ResponseEntity.ok(mapToContractResponseDTO(savedContract, subcontractResponseDTOS));
    }

    public ResponseEntity<ContractResponseDTO> getByStudentGroupId(Long groupId, Long studentId) throws EntityNotFoundException {
        Optional<Contract> contractOptional = contractRepository.findByStudentIdAndGroupId(studentId, groupId);
        if (contractOptional.isEmpty())
            throw new EntityNotFoundException("contract with groupId %s and student id %s not found ".formatted(groupId, studentId));

        Contract contract = contractOptional.get();
        List<Subcontract> subcontracts = subcontractRepository.findAllByContractId(contract.getId());
        if (subcontracts.isEmpty())
            throw new RuntimeException("subcontracts for this contract have not been added ");

        List<SubcontractResponseDTO> responseDTOS = SubcontractMapper.modelToResponseAll(subcontracts);
        return ResponseEntity.ok(mapToContractResponseDTO(contract, responseDTOS));
    }

    public ResponseEntity<List<ContractResponseDTO>> allByStudentId(Long studentId) {
        List<Contract> contracts = contractRepository.finAllByStudentId(studentId);
        if (contracts.isEmpty())
            return ResponseEntity.notFound().build();
        // throw new RuntimeException("any contract with student id %s not found ".formatted(studentId));

        List<ContractResponseDTO> responseDTOS = new ArrayList<>();
        List<Subcontract> subcontracts;
        List<SubcontractResponseDTO> subResponseDTOs;
        for (Contract contract : contracts) {
            subcontracts = subcontractRepository.findAllByContractId(contract.getId());
            subResponseDTOs = SubcontractMapper.modelToResponseAll(subcontracts);
            responseDTOS.add(mapToContractResponseDTO(contract, subResponseDTOs));
        }

        return ResponseEntity.ok(responseDTOS);
    }

    public ResponseEntity<String> deleteByStudentGroupId(Long studentId, Long groupId) throws EntityNotFoundException {
        Optional<Contract> contractOptional = contractRepository.findByStudentIdAndGroupId(studentId, groupId);
        if (contractOptional.isEmpty())
            throw new EntityNotFoundException("group with student id %s and group id %s ".formatted(studentId, groupId));

        Contract contract = contractOptional.get();
        List<Subcontract> subsByContractId = subcontractRepository.findAllByContractId(contract.getId());
        subcontractRepository.deleteAll(subsByContractId);
        contractRepository.deleteById(contract.getId());
        return ResponseEntity.ok("successfully deleted");
    }

    private List<SubcontractResponseDTO> mapToSubcontractResponses(List<SubcontractRequestDTO> subcontractRequests) {
        List<SubcontractResponseDTO> responseDTOS = new ArrayList<>();
        SubcontractResponseDTO responseDTO;
        for (SubcontractRequestDTO request : subcontractRequests) {
            responseDTO = SubcontractResponseDTO.builder()
                    .dateOfMonth(request.getDateOfMonth())
                    .amountEachMonth(new BigDecimal(request.getAmountEachMonth()))
                    .build();
            responseDTOS.add(responseDTO);
        }
        return responseDTOS;
    }

    private ContractResponseDTO mapToContractResponseDTO(Contract contract, List<SubcontractResponseDTO> subcontractResponseDTOList) {
        return ContractResponseDTO.builder()
                .durationOfCourse(contract.getDurationOfCourse())
                .studentName(contract.getStudent().getName())
                .groupName(contract.getGroup().getName())
                .createdAt(contract.getCreatedAt())
                .updatedAt(contract.getUpdatedAt())
                .paymentAmount(contract.getPaymentAmount())
                .responseDTOList(subcontractResponseDTOList)
                .build();
    }

    private Subcontract mapToSubcontract(SubcontractRequestDTO requestDTO, Contract contract) {
        return Subcontract.builder()
                .amountEachMonth(new BigDecimal(requestDTO.getAmountEachMonth()))
                .dateOfMonth(requestDTO.getDateOfMonth())
                .contract(contract)
                .isActive(true)
                .build();
    }

    private Contract mapToContract(ContractRequestDTO requestDTO, Student student, Group group) {
        BigDecimal bigDecimal = new BigDecimal(requestDTO.getPaymentAmount());
        return Contract.builder()
                .student(student)
                .group(group)
                .paymentAmount(bigDecimal)
                .status(ContractStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();
    }


    //

        public ResponseEntity<ContractTeacherResponseDTO> createTeacherContract(ContractTeacherRequestDTO requestDTO) throws EntityNotFoundException {

        Long teacherId = requestDTO.getTeacherId();

        Optional<Contract> contractOptional = contractRepository.findByTeacherId(teacherId);
        if (contractOptional.isPresent())
            throw new RuntimeException("contract with teacherId %s already exists ".formatted(teacherId));

        Optional<Teacher> teacherOptional = teacherRepository.findById(requestDTO.getTeacherId());
        if (teacherOptional.isEmpty())
            throw new EntityNotFoundException("teacher with id %s not found ".formatted(teacherId));

        Contract teacherContract = mapToTeacherContract(requestDTO, teacherOptional.get());
        Contract savedTeacherContract = contractRepository.save(teacherContract);

        return ResponseEntity.ok(mapToContractTeacherResponseDTO(savedTeacherContract));
    }



    public ResponseEntity<ContractTeacherResponseDTO> updateTeacherContract(ContractTeacherRequestDTO requestDTO) throws EntityNotFoundException {
       Long teacherId = requestDTO.getTeacherId();

        Optional<Contract> contractOptional = contractRepository.findByTeacherId(teacherId);
        if (contractOptional.isEmpty())
            throw new EntityNotFoundException("contract with teacher id %s not found "
                    .formatted(teacherId));

        Contract contract = contractOptional.get();
        Optional<Teacher> teacherOptional = teacherRepository.findById(teacherId);
        if (teacherOptional.isEmpty())
            throw new EntityNotFoundException("teacher with id %s not found ".formatted(teacherId));

        Teacher teacher = teacherOptional.get();

        BigDecimal teacherSalary = new BigDecimal(requestDTO.getTeacherSalary());
        contract.setTeacher(teacher);
        contract.setDuration(requestDTO.getDuration());
        contract.setTeacherSalary(String.valueOf(teacherSalary));
        contract.setContractConditions(requestDTO.getContractConditions());
        contract.setIsTeacherAccepted(requestDTO.getIsTeacherAccepted());
        contract.setUpdatedAt(LocalDateTime.now());
        contract.setCreatedAt(LocalDateTime.now());

        Contract savedContract = contractRepository.save(contract);

        return ResponseEntity.ok(mapToContractTeacherResponseDTO(savedContract));
    }

    public ResponseEntity<String> deleteContractByTeacherId(Long teacherId) throws EntityNotFoundException {
        Optional<Contract> contractOptional = contractRepository.findByTeacherId(teacherId);
        if (contractOptional.isEmpty())
            throw new EntityNotFoundException("contract is not found with teacher id ".formatted(teacherId));

        Contract contract = contractOptional.get();
        teacherRepository.deleteById(contract.getId());
        return ResponseEntity.ok("successfully deleted");
    }


    public ResponseEntity<ContractTeacherResponseDTO> getByTeacherId(Long teacherId) throws EntityNotFoundException {
        Optional<Contract> contractOptional = contractRepository.findByTeacherId(teacherId);
        if (contractOptional.isEmpty())
            throw new EntityNotFoundException("contract with teacherId %s not found ".formatted(teacherId));

        Contract contract = contractOptional.get();

        return ResponseEntity.ok(mapToContractTeacherResponseDTO(contract));
    }

    private Contract mapToTeacherContract(ContractTeacherRequestDTO requestDTO, Teacher teacher) {
        BigDecimal bigDecimal = new BigDecimal(requestDTO.getTeacherSalary());
        return Contract.builder()
                .teacher(teacher)
                .duration(requestDTO.getDuration())
                .teacherSalary(String.valueOf(bigDecimal))
                .contractConditions(requestDTO.getContractConditions())
                .isTeacherAccepted(requestDTO.getIsTeacherAccepted())
                .build();
    }

    private ContractTeacherResponseDTO mapToContractTeacherResponseDTO(Contract contract) {
        return ContractTeacherResponseDTO.builder()
                .teacherId(contract.getTeacher().getId())
                .duration(contract.getDuration())
                .teacherSalary(contract.getTeacherSalary())
                .contractConditions(contract.getContractConditions())
                .isTeacherAccepted(contract.getIsTeacherAccepted())
                .updatedAt(contract.getUpdatedAt())
                .createdAt(contract.getCreatedAt())
                .build();
    }

}
