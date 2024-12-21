package com.backend.studycenter.sc_trainerize.mapper.test;

import com.backend.studycenter.sc_trainerize.dto.test.AnswerDTO;
import com.backend.studycenter.sc_trainerize.model.test.Answer;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnswerMapper {
    AnswerMapper INSTANCE = Mappers.getMapper(AnswerMapper.class);

    static List<AnswerDTO> mapToDTO(List<Answer> answers) {
        if (answers == null) {
            return null;
        }

        List<AnswerDTO> list = new ArrayList<>(answers.size());
        for (Answer answer : answers) {
            list.add(INSTANCE.toDTO(answer));
        }

        return list;
    }

    AnswerDTO toDTO(Answer answer);

    Answer updateAnswer(@MappingTarget Answer answer, AnswerDTO answerDTO);

    Answer toModel(AnswerDTO answerDTO);
}
