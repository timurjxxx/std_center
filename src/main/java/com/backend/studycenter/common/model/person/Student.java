package com.backend.studycenter.common.model.person;

import com.backend.studycenter.common.model.valueobjects.Status;
import com.backend.studycenter.scpay.model.Contract;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;


@Entity
//@Table(name = "student")
public class Student extends Person {
    private Integer level;
    private String education;
    private String english;
    private String additionalInformation;
    private Status status;

    @OneToMany(mappedBy = "student")
    private List<Contract> contractControls;


    public Student() {
    }

    public Student(Integer level, String education, String english, String additionalInformation, Status status,List<Contract> contractControls) {
        this.level = level;
        this.education = education;
        this.english = english;
        this.additionalInformation = additionalInformation;
        this.status = status;
        this.contractControls = contractControls;
    }

    public Student(Long id, String name, String description, String address, LocalDate dateOfBirth, LocalDate dateOfRegister, String phone, String email, String telegramUserName, Integer level, String education, String english, String additionalInformation, Status status) {
        super(id, name, description, address, dateOfBirth, dateOfRegister, phone, email, telegramUserName);
        this.level = level;
        this.education = education;
        this.english = english;
        this.additionalInformation = additionalInformation;
        this.status = status;
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
