package com.backend.studycenter.common.service.security;

import com.backend.studycenter.common.dto.security.RoleDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.security.RoleMapper;
import com.backend.studycenter.common.model.security.Role;
import com.backend.studycenter.common.repository.security.RoleRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public ArrayList<Role> getRoles() {
        return (ArrayList<Role>) roleRepository.findAll();
    }

    public Role getRoleById(Long roleId) throws EntityNotFoundException {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Not found role by id = " + roleId));
        return role;
    }

    public Role addRole(RoleDTO roleDTO) {
        return roleRepository.save(RoleMapper.INSTANCE.toModel(roleDTO));
    }

    public void deleteRole(Long roleId) throws EntityNotFoundException {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Not found role by id = " + roleId));
        roleRepository.delete(role);
    }

    public Role updateRole(RoleDTO roleDTO, Long roleId) throws EntityNotFoundException {
        Role existingRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException("Not found role by id = " + roleId));
        Role updatedRole = RoleMapper.INSTANCE.toModel(roleDTO);
        updatedRole.setId(existingRole.getId());
        return roleRepository.save(updatedRole);
    }

    public void clear() {
        roleRepository.deleteAll();
    }
}
