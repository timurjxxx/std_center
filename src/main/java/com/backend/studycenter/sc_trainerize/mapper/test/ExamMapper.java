package com.backend.studycenter.sc_trainerize.mapper.test;

import com.backend.studycenter.sc_trainerize.dto.test.ExamDTO;
import com.backend.studycenter.sc_trainerize.model.test.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExamMapper {
    ExamMapper INSTANCE = Mappers.getMapper(ExamMapper.class);

    ExamDTO toDTO(Exam exam);

    Exam toModel(ExamDTO examDTO);
}
