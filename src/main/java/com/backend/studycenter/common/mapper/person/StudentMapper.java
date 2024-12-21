package com.backend.studycenter.common.mapper.person;

import com.backend.studycenter.common.dto.person.StudentDTO;
import com.backend.studycenter.common.model.person.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO toDTO(Student student);

    Student toModel(StudentDTO studentDTO);
}
