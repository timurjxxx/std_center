package com.backend.studycenter.common.mapper.education;

import com.backend.studycenter.common.dto.education.WeekDayAndTimeDTO;
import com.backend.studycenter.common.model.education.WeekDayAndTime;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WeekDayAndTimeMapper {
    WeekDayAndTimeMapper INSTANCE = Mappers.getMapper(WeekDayAndTimeMapper.class);

    WeekDayAndTimeDTO toDTO(WeekDayAndTime weekDayAndTime);

    WeekDayAndTime toModel(WeekDayAndTimeDTO weekDayAndTimeDTO);

}
