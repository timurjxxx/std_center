package com.backend.studycenter.common.mapper.person;

import com.backend.studycenter.common.dto.person.InterviewDTO;
import com.backend.studycenter.common.model.person.Interview;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterviewMapper {
    InterviewMapper INSTANCE = Mappers.getMapper(InterviewMapper.class);

    InterviewDTO toDTO(Interview interview);

    Interview toModel(InterviewDTO interviewDTO);
}
