package com.backend.studycenter.common.mapper.person;

import com.backend.studycenter.common.dto.person.TeacherDTO;
import com.backend.studycenter.common.model.person.Teacher;
import java.util.ArrayList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    //@Mapping(source = "dateOfBirth", target = "dateOfBirth", dateFormat = "dd-MM-yyyy")
    //@Mapping(source = "dateOfRegister", target = "dateOfRegister", dateFormat = "dd-MM-yyyy")
    TeacherDTO toDTO(Teacher teacher);

    ArrayList<TeacherDTO> toDTO(ArrayList<Teacher> teachers);

    //@Mapping(source = "dateOfBirth", target = "dateOfBirth", dateFormat = "dd-MM-yyyy")
    //@Mapping(source = "dateOfRegister", target = "dateOfRegister", dateFormat = "dd-MM-yyyy")
    Teacher toModel(TeacherDTO teacherDTO);
}
