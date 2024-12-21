package com.backend.studycenter.common.dto.course;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LessonDTO {
    private Long id;
    @NotNull
    @Size(max = 50)
    private String name;
    private String description;

    public LessonDTO() {
    }

    public LessonDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
