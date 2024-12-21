package com.backend.studycenter.common.dto.person;

import com.backend.studycenter.common.model.valueobjects.Status;
import java.time.LocalDate;

public class StudentDTO {
    private Long id;
    private String name;
    private String description;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDate dateOfRegister;
    private String phone;
    private String email;
    private String telegramUserName;
    private Integer level;
    private String education;
    private String english;
    private String additionalInformation;
    private Status status;

    public StudentDTO() {
    }

    public StudentDTO(Long id, String name, String description, String address, LocalDate dateOfBirth, LocalDate dateOfRegister, String phone, String email, String telegramUserName, Integer level, String education, String english, String additionalInformation, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.dateOfRegister = dateOfRegister;
        this.phone = phone;
        this.email = email;
        this.telegramUserName = telegramUserName;
        this.level = level;
        this.education = education;
        this.english = english;
        this.additionalInformation = additionalInformation;
        this.status = status;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
