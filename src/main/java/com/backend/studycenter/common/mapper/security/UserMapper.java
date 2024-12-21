package com.backend.studycenter.common.mapper.security;

import com.backend.studycenter.common.dto.security.UserDTO;
import com.backend.studycenter.common.model.security.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "roles", target = "roleDTOs")
    UserDTO toDTO(User user);

    @Mapping(source = "roleDTOs", target = "roles")
    User toModel(UserDTO userDTO);
}
