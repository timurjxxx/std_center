package com.backend.studycenter.sc_trainerize.mapper.test;

import com.backend.studycenter.sc_trainerize.dto.test.TestDTO;
import com.backend.studycenter.sc_trainerize.model.test.Test;
import java.util.ArrayList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public interface TestMapper {
    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

    @Mapping(source = "questions", target = "questionDTOS")
    TestDTO toDTO(Test test);

    @Mapping(source = "questionDTOS", target = "questions")
    Test toModel(TestDTO testDTO);

    @Mapping(source = "testDTO.questionDTOS", target = "test.questions")
    Test update(@MappingTarget Test test, TestDTO testDTO);

    static List<TestDTO> mapToDTO(List<Test> tests) {
        if (tests == null) {
            return null;
        }

        List<TestDTO> list = new ArrayList<TestDTO>(tests.size());
        for (Test test : tests) {
            list.add(INSTANCE.toDTO(test));
        }

        return list;
    }
}
