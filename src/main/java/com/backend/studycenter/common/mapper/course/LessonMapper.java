package com.backend.studycenter.common.mapper.course;

import com.backend.studycenter.common.dto.course.LessonDTO;
import com.backend.studycenter.common.model.course.Lesson;
import java.util.ArrayList;

public class LessonMapper {
    public static LessonDTO toDTO(Lesson lesson) {
        LessonDTO dto = new LessonDTO();
        dto.setId(lesson.getId());
        dto.setName(lesson.getName());
        dto.setDescription(lesson.getDescription());
        return dto;
    }

    public static ArrayList<LessonDTO> toDTO(ArrayList<Lesson> lessons) {
        ArrayList<LessonDTO> lessonDTOs = new ArrayList<>();
        for (Lesson lesson : lessons) {
            lessonDTOs.add(toDTO(lesson));
        }
        return lessonDTOs;
    }

    public static Lesson toModel(LessonDTO dto) {
        Lesson lesson = new Lesson();
        lesson.setId(dto.getId());
        lesson.setName(dto.getName());
        lesson.setDescription(dto.getDescription());
        return lesson;
    }

    public static Lesson toModel(LessonDTO dto, Lesson lesson) {
        lesson.setName(dto.getName());
        lesson.setDescription(dto.getDescription());
        return lesson;
    }
}
