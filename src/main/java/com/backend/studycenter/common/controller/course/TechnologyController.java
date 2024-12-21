package com.backend.studycenter.common.controller.course;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.dto.course.TechnologyDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.course.TechnologyMapper;
import com.backend.studycenter.common.model.course.Technology;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.common.service.course.TechnologyService;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class TechnologyController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private TechnologyService technologyService;

    
    @GetMapping(value = "/api/v1/technologies")
    public ResponseEntity<ArrayList<TechnologyDTO>> getTechnologies() {
        ArrayList<Technology> technologies = technologyService.getTechnologies();
        ArrayList<TechnologyDTO> technologyDTOs = new ArrayList<>();
        for (Technology technology : technologies) {
            technologyDTOs.add(TechnologyMapper.toDTO(technology));
        }
        if (technologyDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(technologyDTOs);
        }
    }

    
    @GetMapping(value = "/api/v1/technologies/{id}")
    public ResponseEntity<TechnologyDTO> getTechnologyById(@PathVariable(name = "id") Long technologyId) throws EntityNotFoundException {
        TechnologyDTO technologyDTO = TechnologyMapper.toDTO(technologyService.getTechnologyById(technologyId));
        if (technologyDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(technologyDTO);
        }
    }

    
    @PostMapping(value = "/api/v1/technologies", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TechnologyDTO> addTechnology(@RequestBody TechnologyDTO technologyDTO) {
        try {
            return ResponseEntity.ok(TechnologyMapper.toDTO(technologyService.addTechnology(technologyDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PutMapping(value = "/api/v1/technologies/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TechnologyDTO> updateTechnology(@RequestBody TechnologyDTO technologyDTO, @PathVariable(name = "id") Long technologyId) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(TechnologyMapper.toDTO(technologyService.updateTechnology(technologyDTO, technologyId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @DeleteMapping(value = "/api/v1/technologies/{id}")
    public ResponseEntity<Void> deleteTechnology(@PathVariable(name = "id") Long technologyId) throws EntityNotFoundException {
        try {
            technologyService.deleteTechnology(technologyId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
