package com.backend.studycenter.common.mapper.person;

import com.backend.studycenter.common.dto.person.PersonDTO;
import com.backend.studycenter.common.model.person.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO toDTO(Person person);

    Person toModel(PersonDTO personDTO);
}
