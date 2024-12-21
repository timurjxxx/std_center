package com.backend.studycenter.common.controller.security;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.dto.security.RoleDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.security.RoleMapper;
import com.backend.studycenter.common.model.security.Role;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.common.service.security.RoleService;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);

    @Autowired
    private RoleService roleService;


    @GetMapping(value = "/api/v1/roles")
    public ResponseEntity<ArrayList<RoleDTO>> getRoles() {
        ArrayList<Role> roles = roleService.getRoles();
        ArrayList<RoleDTO> roleDTOs = new ArrayList<>();
        for (Role role : roles) {
            roleDTOs.add(RoleMapper.INSTANCE.toDTO(role));
        }
        if (roleDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(roleDTOs);
        }
    }


    @GetMapping(value = "/api/v1/roles/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable(name = "id") Long roleId) throws EntityNotFoundException {
        RoleDTO roleDTO = RoleMapper.INSTANCE.toDTO(roleService.getRoleById(roleId));
        if (roleDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(roleDTO);
        }
    }

    
    @PostMapping(value = "/api/v1/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDTO> addRole(@RequestBody RoleDTO roleDTO) {
        try {
            return ResponseEntity.ok(RoleMapper.INSTANCE.toDTO(roleService.addRole(roleDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PutMapping(value = "/api/v1/roles/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO roleDTO, @PathVariable(name = "id") Long roleId) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(RoleMapper.INSTANCE.toDTO(roleService.updateRole(roleDTO, roleId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @DeleteMapping(value = "/api/v1/roles/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable(name = "id") Long roleId) throws EntityNotFoundException {
        try {
            roleService.deleteRole(roleId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
