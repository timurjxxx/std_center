package com.backend.studycenter.scteach.mapper;

import com.backend.studycenter.scteach.dto.HomeworkDTO;
import com.backend.studycenter.scteach.model.Homework;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HomeworkMapper {
    HomeworkMapper INSTANCE = Mappers.getMapper(HomeworkMapper.class);

    //    @Mapping(source = "homework", target = "homeworkDTO")
    Homework toModel(HomeworkDTO homeworkDTO);

    //    @Mapping(source = "homeworkDTO", target = "homework")
    HomeworkDTO toDTO(Homework attendance);

    Homework toModel(@MappingTarget Homework homework, HomeworkDTO homeworkDTO);

    static List<HomeworkDTO> toDTOs(@NotNull List<Homework> homework) {
        return homework.stream().map((homework1 -> new HomeworkDTO(homework1.getId(),
                homework1.getStudentId(), homework1.getAssessmentID(),
                homework1.getFile()))).collect(Collectors.toList());
    }
}
