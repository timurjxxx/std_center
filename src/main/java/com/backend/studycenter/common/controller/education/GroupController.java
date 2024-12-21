package com.backend.studycenter.common.controller.education;

import com.backend.studycenter.common.controller.person.InterviewController;
import com.backend.studycenter.common.dto.education.GroupDTO;
import com.backend.studycenter.common.dto.education.WeekDayAndTimeDTO;
import com.backend.studycenter.common.dto.person.StudentDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.exception.NotValidException;
import com.backend.studycenter.common.mapper.education.GroupMapper;
import com.backend.studycenter.common.mapper.education.WeekDayAndTimeMapper;
import com.backend.studycenter.common.mapper.person.StudentMapper;
import com.backend.studycenter.common.model.education.Group;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.common.service.education.GroupService;
import com.backend.studycenter.common.service.education.WeekDayAndTimeService;
import com.backend.studycenter.common.service.person.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
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
public class GroupController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private GroupService groupService;
    @Autowired
    private WeekDayAndTimeService weekDayAndTimeService;
    @Autowired
    private StudentService studentService;

    
    @GetMapping(value = "/api/v1/groups")
    public ResponseEntity<ArrayList<GroupDTO>> getGroups() {
        ArrayList<Group> groups = groupService.getGroups();
        ArrayList<GroupDTO> groupDTOs = new ArrayList<>();
        for (Group group : groups) {
            groupDTOs.add(GroupMapper.INSTANCE.toDTO(group));
        }
        if (groupDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(groupDTOs);
        }
    }

    
    @GetMapping(value = "/api/v1/groups/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable(name = "id") Long groupId) throws EntityNotFoundException {
        GroupDTO groupDTO = GroupMapper.INSTANCE.toDTO(groupService.getGroupById(groupId));
        if (groupDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(groupDTO);
        }
    }

    
    @PostMapping(value = "/api/v1/groups", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDTO> addGroup(@RequestBody GroupDTO groupDTO) {
        try {
            return ResponseEntity.ok(GroupMapper.INSTANCE.toDTO(groupService.addGroup(groupDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PutMapping(value = "/api/v1/groups/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDTO> updateGroup(@RequestBody GroupDTO groupDTO, @PathVariable(name = "id") Long groupId) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(GroupMapper.INSTANCE.toDTO(groupService.updateGroup(groupDTO, groupId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @DeleteMapping(value = "/api/v1/groups/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable(name = "id") Long groupId) throws EntityNotFoundException {
        try {
            groupService.deleteGroup(groupId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PostMapping(value = "/api/v1/groups/{groupId}/add-wdt/{weekDayAndTimeId}")
    public ResponseEntity<GroupDTO> addWeekDayAndTimeToGroup(HttpServletRequest request, @PathVariable(name = "groupId") Long groupId, @PathVariable(name = "weekDayAndTimeId") Long weekDayAndTimeId) throws EntityNotFoundException {
        GroupDTO groupDTO = GroupMapper.INSTANCE.toDTO(groupService.getGroupById(groupId));
        WeekDayAndTimeDTO weekDayAndTimeDTO = WeekDayAndTimeMapper.INSTANCE.toDTO(weekDayAndTimeService.getWeekDayAndTimeById(weekDayAndTimeId));
        if (groupDTO == null || weekDayAndTimeDTO == null) {
            return ResponseEntity.notFound().build();
        }
        List<WeekDayAndTimeDTO> weekDayAndTimeDTOs = groupDTO.getWeekDayAndTimeDTOs();
        if (!weekDayAndTimeDTOs.contains(weekDayAndTimeDTO) || weekDayAndTimeDTOs.isEmpty()) {
            weekDayAndTimeDTOs.add(weekDayAndTimeDTO);
            groupDTO.setWeekDayAndTimeDTOs(weekDayAndTimeDTOs);
            groupService.updateGroup(groupDTO, groupId);
            return ResponseEntity.ok(groupDTO);
        } else {
            logger.error("Issue is happened in method " + util.getMethodName() + ". With URI: " + util.getFullUri(request));
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PostMapping(value = "/api/v1/groups/{groupId}/add-student/{studentId}")
    public ResponseEntity<GroupDTO> addStudentToGroup(HttpServletRequest request, @PathVariable(name = "groupId") Long groupId, @PathVariable(name = "studentId") Long studentId) throws EntityNotFoundException {
        GroupDTO groupDTO = GroupMapper.INSTANCE.toDTO(groupService.getGroupById(groupId));
        StudentDTO studentDTO = StudentMapper.INSTANCE.toDTO(studentService.getStudentById(studentId));
        if (groupDTO == null || studentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        List<StudentDTO> studentDTOs = groupDTO.getStudentDTOs();
        if (!studentDTOs.contains(studentDTO) || studentDTOs.isEmpty()) {
            studentDTOs.add(studentDTO);
            groupDTO.setStudentDTOs(studentDTOs);
            groupService.updateGroup(groupDTO, groupId);
            return ResponseEntity.ok(groupDTO);
        } else {
            logger.error("Issue is happened in method " + util.getMethodName() + ". With URI: " + util.getFullUri(request));
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PostMapping(value = "/api/v1/groups/start-group/{id}")
    public ResponseEntity<Void> startGroup(@PathVariable(name = "id") Long groupId) throws EntityNotFoundException, NotValidException {
        try {
            groupService.startGroup(groupId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (NotValidException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
