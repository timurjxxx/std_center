package com.backend.studycenter.scteach.service;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.scteach.dto.HomeworkDTO;
import com.backend.studycenter.scteach.mapper.HomeworkMapper;
import com.backend.studycenter.scteach.model.Homework;
import com.backend.studycenter.scteach.repository.HomeworkRepository;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;

    public List<Homework> getHomeworks() {
        return (List<Homework>) homeworkRepository.findAll();
    }

    public Homework getHomeworkById(@NotNull Long id) throws EntityNotFoundException {
        return homeworkRepository.findById(id).orElseThrow(() -> new
                EntityNotFoundException("Not found homework with id = \"" + id));
    }

    public Homework addHomework(@NotNull HomeworkDTO homeworkDTO) {
        return homeworkRepository.save(HomeworkMapper.INSTANCE.toModel(homeworkDTO));
    }

    public Homework deleteHomeworkById(@NotNull Long id) throws EntityNotFoundException {
        Homework homework = homeworkRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found homework with id = \"" + id));
        homeworkRepository.delete(homework);
        return homework;
    }

    public Homework update(@NotNull Long homeworkId, HomeworkDTO homeworkDTO) throws EntityNotFoundException {
        return HomeworkMapper.INSTANCE.toModel(homeworkRepository.
                findById(homeworkId).orElseThrow(() ->
                        new EntityNotFoundException("Not found homework with id = \"" + homeworkId)), homeworkDTO);
    }

    public void clear() {
        homeworkRepository.deleteAll();
    }

}
