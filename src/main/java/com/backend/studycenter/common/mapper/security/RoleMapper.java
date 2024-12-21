package com.backend.studycenter.common.mapper.security;

import com.backend.studycenter.common.dto.security.RoleDTO;
import com.backend.studycenter.common.model.security.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO toDTO(Role role);

    Role toModel(RoleDTO roleDTO);
}
