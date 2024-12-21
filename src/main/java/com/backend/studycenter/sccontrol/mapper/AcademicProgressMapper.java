package com.backend.studycenter.sccontrol.mapper;

import com.backend.studycenter.sccontrol.dto.AcademicProgressDTO;
import com.backend.studycenter.sccontrol.dto.OfferDTO;
import com.backend.studycenter.sccontrol.model.AcademicProgress;
import com.backend.studycenter.sccontrol.model.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AcademicProgressMapper {
    AcademicProgressMapper INSTANCE = Mappers.getMapper(AcademicProgressMapper.class);

    AcademicProgressDTO toDTO(AcademicProgress academicProgress);

    AcademicProgress  toModel(AcademicProgressDTO academicProgressDTO);
}
