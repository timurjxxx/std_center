package com.backend.studycenter.sc_trainerize.mapper.material;

import com.backend.studycenter.sc_trainerize.dto.material.MaterialDTO;
import com.backend.studycenter.sc_trainerize.model.material.Material;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaterialMapper {
    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    MaterialDTO toDTO(Material material);

    Material toModel(MaterialDTO materialDTO);
}
