package com.backend.studycenter.common.dto.course;

import com.backend.studycenter.common.dto.person.TeacherDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class CourseInfoDTO {
    private Long id;
    @NotNull
    @Size(max = 50)
    private String name;
    private String description;
    private Integer duration;
    private BigDecimal price;

    @JsonProperty("lessons")
    private List<LessonDTO> lessonDTOs;
    @JsonProperty("technologies")
    private List<TechnologyDTO> technologyDTOs;
    @JsonProperty("teachers")
    private List<TeacherDTO> teacherDTOs;

    public CourseInfoDTO() {
    }

    public CourseInfoDTO(Long id, String name, String description, Integer duration, BigDecimal price, List<LessonDTO> lessonDTOs, List<TechnologyDTO> technologyDTOs, List<TeacherDTO> teacherDTOs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
        this.lessonDTOs = lessonDTOs;
        this.technologyDTOs = technologyDTOs;
        this.teacherDTOs = teacherDTOs;
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

    public List<TeacherDTO> getTeacherDTOs() {
        return teacherDTOs;
    }

    public void setTeacherDTOs(List<TeacherDTO> teacherDTOs) {
        this.teacherDTOs = teacherDTOs;
    }
}
