package com.backend.studycenter.common.service.education;

import com.backend.studycenter.common.dto.education.WeekDayAndTimeDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.education.WeekDayAndTimeMapper;
import com.backend.studycenter.common.model.education.WeekDayAndTime;
import com.backend.studycenter.common.repository.education.WeekDayAndTimeRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeekDayAndTimeService {
    @Autowired
    private WeekDayAndTimeRepository weekDayAndTimeRepository;

    public ArrayList<WeekDayAndTime> getWeekDayAndTimes() {
        return (ArrayList<WeekDayAndTime>) weekDayAndTimeRepository.findAll();
    }

    public WeekDayAndTime getWeekDayAndTimeById(Long weekDayAndTimeId) throws EntityNotFoundException {
        WeekDayAndTime weekDayAndTime = weekDayAndTimeRepository.findById(weekDayAndTimeId)
                .orElseThrow(() -> new EntityNotFoundException("Not found weekDayAndTime by id = " + weekDayAndTimeId));
        return weekDayAndTime;
    }

    public WeekDayAndTime addWeekDayAndTime(WeekDayAndTimeDTO weekDayAndTimeDTO) {
        return weekDayAndTimeRepository.save(WeekDayAndTimeMapper.INSTANCE.toModel(weekDayAndTimeDTO));
    }

    public void deleteWeekDayAndTime(Long weekDayAndTimeId) throws EntityNotFoundException {
        WeekDayAndTime weekDayAndTime = weekDayAndTimeRepository.findById(weekDayAndTimeId)
                .orElseThrow(() -> new EntityNotFoundException("Not found weekDayAndTime by id = " + weekDayAndTimeId));
        weekDayAndTimeRepository.delete(weekDayAndTime);
    }

    public WeekDayAndTime updateWeekDayAndTime(WeekDayAndTimeDTO weekDayAndTimeDTO, Long weekDayAndTimeId) throws EntityNotFoundException {
        WeekDayAndTime existingWeekDayAndTime = weekDayAndTimeRepository.findById(weekDayAndTimeId)
                .orElseThrow(() -> new EntityNotFoundException("Not found weekDayAndTime by id = " + weekDayAndTimeId));
        WeekDayAndTime updatedWeekDayAndTime = WeekDayAndTimeMapper.INSTANCE.toModel(weekDayAndTimeDTO);
        updatedWeekDayAndTime.setId(existingWeekDayAndTime.getId());
        return weekDayAndTimeRepository.save(updatedWeekDayAndTime);
    }

    public void clear() {
        weekDayAndTimeRepository.deleteAll();
    }
}
