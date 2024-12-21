package com.backend.studycenter.common.model.person;

import com.backend.studycenter.scpay.model.Contract;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
//@Table(name = "teacher")
public class Teacher extends Person {
    private String specialization;

    @OneToOne(mappedBy = "teacher")
    private Contract contractControl;

    public Teacher() {
    }

    public Teacher(String specialization) {
        this.specialization = specialization;
    }

    public Teacher(Long id, String name, String description, String address, LocalDate dateOfBirth, LocalDate dateOfRegister, String phone, String email, String telegramUserName, String specialization) {
        super(id, name, description, address, dateOfBirth, dateOfRegister, phone, email, telegramUserName);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
