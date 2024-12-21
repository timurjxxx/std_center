package com.backend.studycenter.sccontrol.mapper;

import com.backend.studycenter.sccontrol.dto.WebinarDTO;
import com.backend.studycenter.sccontrol.model.Webinar;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface WebinarMapper {
    WebinarMapper INSTANCE = Mappers.getMapper(WebinarMapper.class);
    WebinarDTO toDTO(Webinar webinar);

    Webinar toModel(WebinarDTO webinarDTO);
}
