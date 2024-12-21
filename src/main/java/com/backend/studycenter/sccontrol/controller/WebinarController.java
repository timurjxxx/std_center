package com.backend.studycenter.sccontrol.controller;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.common.service.person.PersonService;
import com.backend.studycenter.configs.rabbitmq.producer.WebinarProducer;
import com.backend.studycenter.sccontrol.dto.WebinarDTO;
import com.backend.studycenter.sccontrol.mapper.WebinarMapper;
import com.backend.studycenter.sccontrol.model.Webinar;
import com.backend.studycenter.sccontrol.service.WebinarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/webinar")

public class WebinarController {
    @Autowired
    WebinarService webinarService;
    @Autowired
    WebinarProducer webinarProducer;
    private final Logger logger = LoggerFactory.getLogger(WebinarController.class);

    @GetMapping(value = "{id}")
    public ResponseEntity<WebinarDTO> getById(@PathVariable(name = "id") Long id) throws EntityNotFoundException {
        WebinarDTO webinarDTO = WebinarMapper.INSTANCE.toDTO(webinarService.getById(id));
        if (webinarDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(webinarDTO);
        }
    }

    @GetMapping
    public ResponseEntity<List<WebinarDTO>> getAll() {
        List<WebinarDTO> webinarDTOS = new ArrayList<>();
        for (Webinar webinar : webinarService.getAllWebinar()) {
            webinarDTOS.add(WebinarMapper.INSTANCE.toDTO(webinar));
        }
        if (webinarDTOS.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(webinarDTOS);
        }

    }

    @PostMapping
    public ResponseEntity<WebinarDTO> addWebinar(@RequestBody WebinarDTO webinarDTO) {
        try {
            WebinarDTO newWebinarDTO = WebinarMapper.INSTANCE.toDTO(webinarService.addWebinar(webinarDTO));
            webinarProducer.sendMessage(newWebinarDTO);
            return ResponseEntity.ok(newWebinarDTO);
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PreAuthorize("hasAuthority('EDITOR')")

    @PutMapping
    public ResponseEntity<WebinarDTO> update(@RequestBody WebinarDTO webinarDTO, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(WebinarMapper.INSTANCE.toDTO(webinarService.updateWebinar(id, webinarDTO)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping

    public ResponseEntity<Void> deleteWebinar(@PathVariable Long id) {
        try {
            webinarService.deleteWebinar(id);
            return ResponseEntity.ok().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
