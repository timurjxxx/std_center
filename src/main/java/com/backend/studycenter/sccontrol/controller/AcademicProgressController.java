package com.backend.studycenter.sccontrol.controller;

import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.sccontrol.dto.AcademicProgressDTO;
import com.backend.studycenter.sccontrol.mapper.AcademicProgressMapper;
import com.backend.studycenter.sccontrol.service.AcademicProgressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/academiProgress")
public class AcademicProgressController {
    private final Logger logger = LoggerFactory.getLogger(AcademicProgressController.class);

    @Autowired
    AcademicProgressService academicProgressService;

    @GetMapping
    public ResponseEntity<AcademicProgressDTO> get(@PathVariable Long courseID, @PathVariable Long studentID) {
        try {
            return ResponseEntity.ok(AcademicProgressMapper.INSTANCE.toDTO(academicProgressService.getAcademicProgress(courseID, studentID)));
        }catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }

    }
}
