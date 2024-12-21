package com.backend.studycenter.sccontrol.mapper;

import com.backend.studycenter.sccontrol.dto.InterviewResultDTO;
import com.backend.studycenter.sccontrol.model.InterviewResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterviewResultMapper {
    InterviewResultMapper INSTANCE = Mappers.getMapper(InterviewResultMapper.class);

    InterviewResultDTO toDTO(InterviewResult interviewResult);

    InterviewResult toModel(InterviewResultDTO interviewResultDTO);

}
