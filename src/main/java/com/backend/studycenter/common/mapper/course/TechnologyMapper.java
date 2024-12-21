package com.backend.studycenter.common.mapper.course;

import com.backend.studycenter.common.dto.course.TechnologyDTO;
import com.backend.studycenter.common.model.course.Technology;
import java.util.ArrayList;

public class TechnologyMapper {
    public static TechnologyDTO toDTO(Technology technology) {
        TechnologyDTO dto = new TechnologyDTO();
        dto.setId(technology.getId());
        dto.setName(technology.getName());
        dto.setDescription(technology.getDescription());
        return dto;
    }

    public static ArrayList<TechnologyDTO> toDTO(ArrayList<Technology> technologies) {
        ArrayList<TechnologyDTO> technologyDTOs = new ArrayList<>();
        for (Technology technology : technologies) {
            technologyDTOs.add(toDTO(technology));
        }
        return technologyDTOs;
    }

    public static Technology toModel(TechnologyDTO dto) {
        Technology technology = new Technology();
        technology.setId(dto.getId());
        technology.setName(dto.getName());
        technology.setDescription(dto.getDescription());
        return technology;
    }

    public static Technology toModel(TechnologyDTO dto, Technology technology) {
        technology.setName(dto.getName());
        technology.setDescription(dto.getDescription());
        return technology;
    }
}
