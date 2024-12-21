package com.backend.studycenter.scteach.mapper;

import com.backend.studycenter.scteach.dto.HomeworkResultDTO;
import com.backend.studycenter.scteach.model.HomeworkResult;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HomeworkResultMapper {
    HomeworkResultMapper INSTANCE = Mappers.getMapper(HomeworkResultMapper.class);

    //    @Mapping(source = "homeworkResult", target = "homeworkResultDTO")
    HomeworkResult toModel(HomeworkResultDTO homeworkResultDTO);

    //    @Mapping(source = "homeworkResultDTO", target = "homeworkResult")
    HomeworkResultDTO toDTO(HomeworkResult attendance);

    HomeworkResult toModel(@MappingTarget HomeworkResult homeworkResult, HomeworkResultDTO homeworkResultDTO);

    static List<HomeworkResultDTO> toDTOs(@NotNull List<HomeworkResult> homeworkResults) {
        return homeworkResults.stream().map((homeworkResult -> new HomeworkResultDTO(homeworkResult.getId(),
                homeworkResult.getStudentId(), homeworkResult.getHomeworkId(),
                homeworkResult.getTeacherID(), homeworkResult.getScore(),
                homeworkResult.getDescription()))).collect(Collectors.toList());
    }
}
