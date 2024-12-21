package com.backend.studycenter.sc_trainerize.model.topic;

import com.backend.studycenter.common.model.person.Person;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Author extends Person {

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Topic> topics;

    public Author() {

    }

    public Author(Long id, String name, String description, String address, LocalDate dateOfBirth, LocalDate dateOfRegister, String phone, String email, String telegramUserName, List<Topic> topics) {
        super(id, name, description, address, dateOfBirth, dateOfRegister, phone, email, telegramUserName);
        this.topics = topics;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
