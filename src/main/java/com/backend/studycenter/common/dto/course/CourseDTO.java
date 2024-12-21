package com.backend.studycenter.common.dto.course;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class CourseDTO {
    private Long id;
    @NotNull
    @Size(max = 150)
    private String name;
    private String description;
    private Integer duration;
    private BigDecimal price;

    private List<LessonDTO> lessonDTOs;
    private List<TechnologyDTO> technologyDTOs;

    public CourseDTO() {
    }

    public CourseDTO(Long id, String name, String description, Integer duration, BigDecimal price, List<LessonDTO> lessonDTOs, List<TechnologyDTO> technologyDTOs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.lessonDTOs = lessonDTOs;
        this.technologyDTOs = technologyDTOs;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<LessonDTO> getLessonDTOs() {
        return lessonDTOs;
    }

    public void setLessonDTOs(List<LessonDTO> lessonDTOs) {
        this.lessonDTOs = lessonDTOs;
    }

    public List<TechnologyDTO> getTechnologyDTOs() {
        return technologyDTOs;
    }

    public void setTechnologyDTOs(List<TechnologyDTO> technologyDTOs) {
        this.technologyDTOs = technologyDTOs;
    }
}
