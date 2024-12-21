package com.backend.studycenter.sc_trainerize.controller.topic;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.dto.topic.AuthorDTO;
import com.backend.studycenter.sc_trainerize.mapper.topic.AuthorMapper;
import com.backend.studycenter.sc_trainerize.model.topic.Author;
import com.backend.studycenter.sc_trainerize.service.topic.AuthorService;
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
@RequestMapping("/api/v1/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAuthors() {
        List<Author> authors = authorService.getAll();
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        for (Author author : authors) {
            authorDTOS.add(AuthorMapper.INSTANCE.toDTO(author));
        }
        if (authorDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.ok(authorDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) throws EntityNotFoundException {
        AuthorDTO authorDTO = AuthorMapper.INSTANCE.toDTO(authorService.getAuthorById(id));
        if (authorDTO == null) {
            return ResponseEntity.noContent().build();
        } else
            return ResponseEntity.ok(authorDTO);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO authorDTO) {
        try {
            return ResponseEntity.ok(AuthorMapper.INSTANCE.toDTO(authorService.addAuthor(authorDTO)));
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) throws EntityNotFoundException {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {

            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO, @PathVariable Long id) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(AuthorMapper.INSTANCE.toDTO(authorService.updateAuthor(authorDTO, id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
