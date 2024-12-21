package com.backend.studycenter.scpay.mapper;

import com.backend.studycenter.scpay.dto.request.DepartmentRequestDTO;
import com.backend.studycenter.scpay.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    //@Mapping(source = "department",target = "departmentDTO")
    DepartmentRequestDTO toDTO(Department department);

  //  @Mapping(source = "departmentRequestDTO",target = "department")
    Department toModel(DepartmentRequestDTO departmentRequestDTO);
}
