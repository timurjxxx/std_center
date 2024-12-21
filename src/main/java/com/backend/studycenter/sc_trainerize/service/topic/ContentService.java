package com.backend.studycenter.sc_trainerize.service.topic;

import com.backend.studycenter.sc_trainerize.dto.topic.ContentDTO;
import com.backend.studycenter.sc_trainerize.mapper.topic.ContentMapper;
import com.backend.studycenter.sc_trainerize.model.topic.Content;
import com.backend.studycenter.sc_trainerize.repository.material.MaterialRepository;
import com.backend.studycenter.sc_trainerize.repository.topic.ContentRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    MaterialRepository materialRepository;

    public List<Content> getAll() {
        return contentRepository.findAll();
    }

    public Content getContentById(Long id) throws com.backend.studycenter.common.exception.EntityNotFoundException {
        Optional<Content> optionalContent = contentRepository.findById(id);
        if (optionalContent.isEmpty()) {
            throw new com.backend.studycenter.common.exception.EntityNotFoundException("Content not found with id=" + id);
        }
        return optionalContent.get();
    }

    public Content addContent(ContentDTO contentDTO) {
        return contentRepository.save(ContentMapper.INSTANCE.toModel(contentDTO));
    }

    public void deleteContent(Long id) throws EntityNotFoundException, com.backend.studycenter.common.exception.EntityNotFoundException {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new com.backend.studycenter.common.exception.EntityNotFoundException("Not found content by id" + id));
        contentRepository.deleteById(id);
    }

    public Content updateContent(ContentDTO contentDTO, Long id) throws EntityNotFoundException, com.backend.studycenter.common.exception.EntityNotFoundException {
        Content existingContent = contentRepository.findById(id)
                .orElseThrow(() -> new com.backend.studycenter.common.exception.EntityNotFoundException("Not found content by id" + id));
        Content updateContent = ContentMapper.INSTANCE.toModel(contentDTO);
        updateContent.setId(existingContent.getId());
        return contentRepository.save(updateContent);
    }
}
