package com.backend.studycenter.common.model.education;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
public class WeekDayAndTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private DayOfWeek dayOfWeek;
    private LocalTime time;

    public WeekDayAndTime() {
    }

    public WeekDayAndTime(Long id, String name, DayOfWeek dayOfWeek, LocalTime time) {
        this.id = id;
        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
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

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
