package com.backend.studycenter.common.service.education;

import com.backend.studycenter.common.dto.education.GroupDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.exception.NotValidException;
import com.backend.studycenter.common.mapper.education.GroupMapper;
import com.backend.studycenter.common.model.course.Lesson;
import com.backend.studycenter.common.model.education.CalendarLesson;
import com.backend.studycenter.common.model.education.Group;
import com.backend.studycenter.common.model.education.WeekDayAndTime;
import com.backend.studycenter.common.repository.education.CalendarLessonRepository;
import com.backend.studycenter.common.repository.education.GroupRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private CalendarLessonRepository calendarLessonRepository;

    public ArrayList<Group> getGroups() {
        return (ArrayList<Group>) groupRepository.findAll();
    }

    public Group getGroupById(Long groupId) throws EntityNotFoundException {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new EntityNotFoundException("Not found group by id = " + groupId));
        return group;
    }

    public Group addGroup(GroupDTO groupDTO) {
        return groupRepository.save(GroupMapper.INSTANCE.toModel(groupDTO));
    }

    public void deleteGroup(Long groupId) throws EntityNotFoundException {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new EntityNotFoundException("Not found group by id = " + groupId));
        groupRepository.delete(group);
    }

    public Group updateGroup(GroupDTO groupDTO, Long groupId) throws EntityNotFoundException {
        Group existingGroup = groupRepository.findById(groupId).orElseThrow(() -> new EntityNotFoundException("Not found group by id = " + groupId));
        Group updatedGroup = GroupMapper.INSTANCE.toModel(groupDTO);
        updatedGroup.setId(existingGroup.getId());
        return groupRepository.save(updatedGroup);
    }

    public void startGroup(Long groupId) throws EntityNotFoundException, NotValidException {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new EntityNotFoundException("Not found group by id = " + groupId));
        String errBegin = "Not valid group with id = " + groupId + ". ";
        String errEnd = null;
        if (group.getStartDate() == null) {
            errEnd = "StartDate is null!";
        } else if (group.getWeekDayAndTimes().isEmpty()) {
            errEnd = "List<WeekDayAndTime> is empty!";
        } else if (group.getTeacher() == null) {
            errEnd = "Teacher is null!";
        } else if (group.getCourse() == null) {
            errEnd = "Course is null!";
        } else if (group.getCourse().getLessons().isEmpty()) {
            errEnd = "List<Lesson> is empty!";
        }

        if (errEnd != null) {
            throw new NotValidException(errBegin + errEnd);
        }

        fillCalendarLessons(group);
    }

    private void fillCalendarLessons(Group group) throws EntityNotFoundException, NotValidException {
        int plusDays = 0;
        LocalDate startDate = group.getStartDate();
        LocalDateTime resultDateTime = null;

        Comparator<Lesson> lessonComparator = Comparator.comparing(Lesson::getId);
        List<Lesson> sortedLessons = group.getCourse().getLessons();
        sortedLessons.sort(lessonComparator);

        for (Lesson lesson : sortedLessons) {
            CalendarLesson calendarLesson = new CalendarLesson();

            calendarLesson.setLesson(lesson);
            calendarLesson.setPlannedTeacher(group.getTeacher());
            resultDateTime = getMinDate(startDate.plusDays(plusDays), group.getWeekDayAndTimes());
            startDate = resultDateTime.toLocalDate();
            calendarLesson.setPlannedLessonDateTime(resultDateTime);

            calendarLessonRepository.save(calendarLesson);
            group.getCalendarLessons().add(calendarLesson);

            plusDays = 1;
        }
        groupRepository.save(group);
    }

    private LocalDateTime getMinDate(LocalDate startDate, List<WeekDayAndTime> weekDayAndTimes) {
        LocalDate minDate = null, tempDate = null;
        LocalTime time = null;

        for (WeekDayAndTime weekDayAndTime : weekDayAndTimes) {
            tempDate = startDate.with(DayOfWeek.of(weekDayAndTime.getDayOfWeek().getValue()));
            if ((tempDate.isAfter(startDate)) && (minDate == null || minDate.isAfter(tempDate))) {
                minDate = tempDate;
                time = weekDayAndTime.getTime();
            }
        }
        //FixMe: remove recursion
        if (minDate == null) {
            return getMinDate(startDate.plusDays(1), weekDayAndTimes);
        } else {
            return LocalDateTime.of(minDate, time);
        }
    }

    public void clear() {
        groupRepository.deleteAll();
    }
}
