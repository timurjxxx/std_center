package com.backend.studycenter.sc_trainerize.mapper.material;

import com.backend.studycenter.sc_trainerize.dto.material.VideoDTO;
import com.backend.studycenter.sc_trainerize.model.material.Video;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VideoMapper {

    VideoMapper INSTANCE = Mappers.getMapper(VideoMapper.class);

    VideoDTO toDTO(Video video);

    Video toModel(VideoDTO videoDTO);


}
