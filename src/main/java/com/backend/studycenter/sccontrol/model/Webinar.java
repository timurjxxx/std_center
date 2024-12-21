package com.backend.studycenter.sccontrol.model;

import com.backend.studycenter.common.model.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "webinar")

public class Webinar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private Person speaker;
    @ManyToMany
    private List<Person> participants;
    private LocalDateTime date;
    private String description;
    private String location;


    public Webinar() {
    }

    public Webinar(Long id, String title, Person speaker, List<Person> participants, LocalDateTime date, String description, String location) {
        this.id = id;
        this.title = title;
        this.speaker = speaker;
        this.participants = participants;
        this.date = date;
        this.description = description;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Person speaker) {
        this.speaker = speaker;
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Person> participants) {
        this.participants = participants;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
