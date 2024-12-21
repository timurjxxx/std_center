package com.backend.studycenter.sc_trainerize.mapper.topic;

import com.backend.studycenter.sc_trainerize.dto.topic.AuthorDTO;
import com.backend.studycenter.sc_trainerize.model.topic.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO toDTO(Author author);

    Author toModel(AuthorDTO authorDTO);
}
