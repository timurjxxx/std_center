package com.backend.studycenter.common.dto.security;

import com.backend.studycenter.common.model.person.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

import static com.backend.studycenter.scpay.constant.AppConstants.DATETIME_FORMAT;

public class UserDTO {
    private Long id;
    @NotNull
    @Size(max = 50)
    private String username;
    private String password;
    private Boolean active;
    private List<RoleDTO> roleDTOs;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime dataCreated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATETIME_FORMAT)
    private LocalDateTime dataExpired;
    private Person person;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String password, Boolean active, List<RoleDTO> roleDTOs, LocalDateTime dataCreated, LocalDateTime dataExpired, Person person) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.roleDTOs = roleDTOs;
        this.dataCreated = dataCreated;
        this.dataExpired = dataExpired;
        this.person = person;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<RoleDTO> getRoleDTOs() {
        return roleDTOs;
    }

    public void setRoleDTOs(List<RoleDTO> roleDTOs) {
        this.roleDTOs = roleDTOs;
    }

    public LocalDateTime getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(LocalDateTime dataCreated) {
        this.dataCreated = dataCreated;
    }

    public LocalDateTime getDataExpired() {
        return dataExpired;
    }

    public void setDataExpired(LocalDateTime dataExpired) {
        this.dataExpired = dataExpired;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
