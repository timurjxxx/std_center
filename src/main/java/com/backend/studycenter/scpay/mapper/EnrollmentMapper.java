package com.backend.studycenter.scpay.mapper;

import com.backend.studycenter.scpay.dto.EnrollmentDTO;
import com.backend.studycenter.scpay.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnrollmentMapper {

    EnrollmentMapper INSTANCE = Mappers.getMapper(EnrollmentMapper.class);

  //  @Mapping(source = "enrollment",target = "enrollmentDTO")
    EnrollmentDTO toDTO(Enrollment enrollment);

   // @Mapping(source = "enrollmentDTO",target = "enrollment")
    Enrollment toModel(EnrollmentDTO enrollmentDTO);
}
