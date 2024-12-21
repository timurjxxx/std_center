package com.backend.studycenter.sc_trainerize.mapper.topic;

import com.backend.studycenter.sc_trainerize.dto.topic.TopicDTO;
import com.backend.studycenter.sc_trainerize.model.topic.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface TopicMapper {
    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    @Mapping(source = "author", target = "authorDTO")
    @Mapping(source = "contents", target = "contentDTOS")
    TopicDTO toDTO(Topic topic);

    @Mapping(source = "authorDTO", target = "author")
    @Mapping(source = "contentDTOS", target = "contents")
    Topic toModel(TopicDTO topicDTO);

}
