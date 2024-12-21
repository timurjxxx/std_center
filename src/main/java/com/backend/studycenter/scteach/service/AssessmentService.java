package com.backend.studycenter.scteach.service;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.scteach.dto.AssessmentDTO;
import com.backend.studycenter.scteach.mapper.AssessmentMapper;
import com.backend.studycenter.scteach.model.Assessment;
import com.backend.studycenter.scteach.repository.AssessmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentService {
    @Autowired
    private AssessmentRepository assessmentRepository;

    public List<Assessment> getAssessments() {
        return (List<Assessment>) assessmentRepository.findAll();
    }

    public Assessment getAssessmentById(Long id) throws EntityNotFoundException {
        return assessmentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found assessment with id = \"" + id));
    }

    public Assessment addAssessment(AssessmentDTO assessmentDTO) {
        return assessmentRepository.save(AssessmentMapper.INSTANCE.toModel(assessmentDTO));
    }

    public Assessment delete(Long id) throws EntityNotFoundException {
        Assessment byId = assessmentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found assessment with id = \"" + id));
        assessmentRepository.delete(byId);
        return byId;
    }

    public Assessment updateAssessment(Long id, AssessmentDTO assessmentDTO) throws EntityNotFoundException {
        Assessment assessment = assessmentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found assessment with id = \"" + id));
        return AssessmentMapper.INSTANCE.toModel(assessment, assessmentDTO);
    }

    public void clear() {
        assessmentRepository.deleteAll();
    }
}
