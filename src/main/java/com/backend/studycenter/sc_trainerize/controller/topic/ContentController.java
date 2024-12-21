package com.backend.studycenter.sc_trainerize.controller.topic;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.dto.topic.ContentDTO;
import com.backend.studycenter.sc_trainerize.mapper.topic.ContentMapper;
import com.backend.studycenter.sc_trainerize.model.topic.Content;
import com.backend.studycenter.sc_trainerize.service.topic.ContentService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/content")
public class ContentController {
    @Autowired
    ContentService contentService;

    @GetMapping
    public ResponseEntity<List<ContentDTO>> getContents() {
        List<Content> contents = contentService.getAll();
        List<ContentDTO> contentDTOS = new ArrayList<>();
        for (Content content : contents) {
            contentDTOS.add(ContentMapper.INSTANCE.toDTO(content));
        }
        if (contentDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.ok(contentDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentDTO> getContentById(@PathVariable Long id) throws EntityNotFoundException {
        ContentDTO contentDTO = ContentMapper.INSTANCE.toDTO(contentService.getContentById(id));
        if (contentDTO == null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(contentDTO);
    }

    @PostMapping
    public ResponseEntity<ContentDTO> addContent(@RequestBody ContentDTO contentDTO) {
        try {
            return ResponseEntity.ok(ContentMapper.INSTANCE.toDTO(contentService.addContent(contentDTO)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) throws EntityNotFoundException {
        try {
            contentService.deleteContent(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentDTO> updateContent(@RequestBody ContentDTO contentDTO, @PathVariable Long id) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(ContentMapper.INSTANCE.toDTO(contentService.updateContent(contentDTO, id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
