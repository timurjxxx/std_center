package com.backend.studycenter.sc_trainerize.mapper.test;

import com.backend.studycenter.sc_trainerize.dto.test.QuestionDTO;
import com.backend.studycenter.sc_trainerize.model.test.Answer;
import com.backend.studycenter.sc_trainerize.model.test.Question;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuestionMapper {
    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    static List<QuestionDTO> mapToDTO(List<Question> questions) {
        if (questions == null) {
            return null;
        }

        List<QuestionDTO> list = new ArrayList<>(questions.size());
        for (Question question : questions) {
            QuestionDTO questionDTO = INSTANCE.toDTO(question);
            List<Answer> answers = question.getAnswers();

            questionDTO.setAnswerDTOS(AnswerMapper.mapToDTO(answers));

            list.add(questionDTO);
        }

        return list;
    }

    @Mapping(source = "answers", target = "answerDTOS")
    QuestionDTO toDTO(Question question);

    @Mapping(source = "answerDTOS", target = "answers")
    Question toModel(QuestionDTO questionDTO);

    Question updateQuestion(@MappingTarget Question question, QuestionDTO questionDTO);
}
