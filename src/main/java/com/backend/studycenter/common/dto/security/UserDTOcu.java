package com.backend.studycenter.common.dto.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

import static com.backend.studycenter.scpay.constant.AppConstants.DATETIME_FORMAT;

public class UserDTOcu {
    private Long id;
    @NotNull
    @Size(max = 50)
    private String username;
    private String password;
    private Long personId;
    private Long[] roleIds;
    private Boolean active;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime dataExpired;

    public UserDTOcu() {
    }

    public UserDTOcu(Long id, String username, String password, Long personId, Long[] roleIds, Boolean active, LocalDateTime dataExpired) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.personId = personId;
        this.roleIds = roleIds;
        this.active = active;
        this.dataExpired = dataExpired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getDataExpired() {
        return dataExpired;
    }

    public void setDataExpired(LocalDateTime dataExpired) {
        this.dataExpired = dataExpired;
    }
}
