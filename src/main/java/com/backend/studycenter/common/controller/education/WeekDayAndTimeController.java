package com.backend.studycenter.common.controller.education;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.dto.education.WeekDayAndTimeDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.education.WeekDayAndTimeMapper;
import com.backend.studycenter.common.model.education.WeekDayAndTime;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.common.service.education.WeekDayAndTimeService;
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
public class WeekDayAndTimeController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private WeekDayAndTimeService weekDayAndTimeService;

    
    @GetMapping(value = "/api/v1/wdt")
    public ResponseEntity<ArrayList<WeekDayAndTimeDTO>> getWeekDayAndTimes() {
        ArrayList<WeekDayAndTime> weekDayAndTimes = weekDayAndTimeService.getWeekDayAndTimes();
        ArrayList<WeekDayAndTimeDTO> weekDayAndTimeDTOs = new ArrayList<>();
        for (WeekDayAndTime weekDayAndTime : weekDayAndTimes) {
            weekDayAndTimeDTOs.add(WeekDayAndTimeMapper.INSTANCE.toDTO(weekDayAndTime));
        }
        if (weekDayAndTimeDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(weekDayAndTimeDTOs);
        }
    }

    
    @GetMapping(value = "/api/v1/wdt/{id}")
    public ResponseEntity<WeekDayAndTimeDTO> getWeekDayAndTimeById(@PathVariable(name = "id") Long weekDayAndTimeId) throws EntityNotFoundException {
        WeekDayAndTimeDTO weekDayAndTimeDTO = WeekDayAndTimeMapper.INSTANCE.toDTO(weekDayAndTimeService.getWeekDayAndTimeById(weekDayAndTimeId));
        if (weekDayAndTimeDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(weekDayAndTimeDTO);
        }
    }

    
    @PostMapping(value = "/api/v1/wdt", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeekDayAndTimeDTO> addWeekDayAndTime(@RequestBody WeekDayAndTimeDTO weekDayAndTimeDTO) {
        try {
            return ResponseEntity.ok(WeekDayAndTimeMapper.INSTANCE.toDTO(weekDayAndTimeService.addWeekDayAndTime(weekDayAndTimeDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PutMapping(value = "/api/v1/wdt/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeekDayAndTimeDTO> updateWeekDayAndTime(@RequestBody WeekDayAndTimeDTO weekDayAndTimeDTO, @PathVariable(name = "id") Long weekDayAndTimeId) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(WeekDayAndTimeMapper.INSTANCE.toDTO(weekDayAndTimeService.updateWeekDayAndTime(weekDayAndTimeDTO, weekDayAndTimeId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @DeleteMapping(value = "/api/v1/wdt/{id}")
    public ResponseEntity<Void> deleteWeekDayAndTime(@PathVariable(name = "id") Long weekDayAndTimeId) throws EntityNotFoundException {
        try {
            weekDayAndTimeService.deleteWeekDayAndTime(weekDayAndTimeId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
