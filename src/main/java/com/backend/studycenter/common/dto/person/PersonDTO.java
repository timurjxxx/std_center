package com.backend.studycenter.common.dto.person;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

import static com.backend.studycenter.scpay.constant.AppConstants.DATETIME_FORMAT;
import static com.backend.studycenter.scpay.constant.AppConstants.DATE_FORMAT;

public class PersonDTO {
    private Long id;
    private String name;
    private String description;
    private String address;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate dateOfBirth;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    private LocalDate dateOfRegister;
    private String phone;
    private String email;
    private String telegramUserName;

    public PersonDTO() {
    }

    public PersonDTO(Long id, String name, String description, String address, LocalDate dateOfBirth, LocalDate dateOfRegister, String phone, String email, String telegramUserName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegister = dateOfRegister;
        this.phone = phone;
        this.email = email;
        this.telegramUserName = telegramUserName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfRegister() {
        return dateOfRegister;
    }

    public void setDateOfRegister(LocalDate dateOfRegister) {
        this.dateOfRegister = dateOfRegister;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegramUserName() {
        return telegramUserName;
    }

    public void setTelegramUserName(String telegramUserName) {
        this.telegramUserName = telegramUserName;
    }
}
