package com.backend.studycenter.sc_trainerize.mapper.topic;

import com.backend.studycenter.sc_trainerize.dto.topic.ContentDTO;
import com.backend.studycenter.sc_trainerize.model.topic.Content;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContentMapper {
    ContentMapper INSTANCE = Mappers.getMapper(ContentMapper.class);

    ContentDTO toDTO(Content content);

    Content toModel(ContentDTO contentDTO);
}
