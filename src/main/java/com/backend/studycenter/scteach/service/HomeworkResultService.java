package com.backend.studycenter.scteach.service;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.scteach.dto.HomeworkResultDTO;
import com.backend.studycenter.scteach.mapper.HomeworkResultMapper;
import com.backend.studycenter.scteach.model.HomeworkResult;
import com.backend.studycenter.scteach.repository.HomeworkResultRepository;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeworkResultService {
    @Autowired
    private HomeworkResultRepository homeworkResultRepository;


    public List<HomeworkResult> getHomeworkResults() {
        return (List<HomeworkResult>) homeworkResultRepository.findAll();
    }

    public HomeworkResult getHomeworkResultById(@NotNull Long id) throws EntityNotFoundException {
        return homeworkResultRepository.findById(id).orElseThrow(() -> new
                EntityNotFoundException("Not found homeworkResult with id = \"" + id));
    }

    public HomeworkResult addHomeworkResult(@NotNull HomeworkResultDTO homeworkResultDTO) {
        return homeworkResultRepository.save(HomeworkResultMapper.INSTANCE.toModel(homeworkResultDTO));
    }

    public HomeworkResult deleteHomeworkResultById(@NotNull Long id) throws EntityNotFoundException {
        HomeworkResult homeworkResult = homeworkResultRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found homeworkResult with id = \"" + id));
        homeworkResultRepository.delete(homeworkResult);
        return homeworkResult;
    }

    public HomeworkResult update(@NotNull Long homeworkResultId, HomeworkResultDTO homeworkResultDTO) throws EntityNotFoundException {
        return HomeworkResultMapper.INSTANCE.toModel(homeworkResultRepository.
                findById(homeworkResultId).orElseThrow(() ->
                        new EntityNotFoundException("Not found homeworkResult with id = \"" + homeworkResultId)), homeworkResultDTO);
    }

    public void clear() {
        homeworkResultRepository.deleteAll();
    }
}
