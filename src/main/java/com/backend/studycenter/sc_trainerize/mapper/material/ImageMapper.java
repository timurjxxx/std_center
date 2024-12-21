package com.backend.studycenter.sc_trainerize.mapper.material;

import com.backend.studycenter.sc_trainerize.dto.material.ImageDTO;
import com.backend.studycenter.sc_trainerize.model.material.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDTO toDTO(Image image);

    Image toModel(ImageDTO imageDTO);

}
