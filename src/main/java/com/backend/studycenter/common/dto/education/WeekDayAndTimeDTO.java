package com.backend.studycenter.common.dto.education;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class WeekDayAndTimeDTO {
    private Long id;
    private String name;
    private DayOfWeek dayOfWeek;
    private LocalTime time;

    public WeekDayAndTimeDTO() {
    }

    public WeekDayAndTimeDTO(Long id, String name, DayOfWeek dayOfWeek, LocalTime time) {
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
