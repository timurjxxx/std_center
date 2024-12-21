package com.backend.studycenter.scteach.mapper;

import com.backend.studycenter.scteach.dto.AssessmentDTO;
import com.backend.studycenter.scteach.model.Assessment;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AssessmentMapper {
    AssessmentMapper INSTANCE = Mappers.getMapper(AssessmentMapper.class);

    //    @Mapping(source = "assessment", target = "assessmentDTO")
    Assessment toModel(AssessmentDTO assessmentDTO);

    //    @Mapping(source = "assessmentDTO", target = "assessment")
    AssessmentDTO toDTO(Assessment assessment);

    Assessment toModel(@MappingTarget Assessment assessment, AssessmentDTO assessmentDTO);

    static List<AssessmentDTO> toDTOs(@NotNull List<Assessment> assessments) {
        return assessments.stream().map((assessment) ->
                new AssessmentDTO(assessment.getId(), assessment.getTeacherID(),
                        assessment.getDescription(), assessment.getGroupID())).toList();
    }
}
