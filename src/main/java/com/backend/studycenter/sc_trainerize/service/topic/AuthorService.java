package com.backend.studycenter.sc_trainerize.service.topic;

import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.sc_trainerize.dto.topic.AuthorDTO;
import com.backend.studycenter.sc_trainerize.mapper.topic.AuthorMapper;
import com.backend.studycenter.sc_trainerize.model.topic.Author;
import com.backend.studycenter.sc_trainerize.repository.topic.AuthorRepository;
import com.backend.studycenter.sc_trainerize.repository.topic.TopicRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    TopicRepository topicRepository;

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) throws EntityNotFoundException {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty()) {
            throw new EntityNotFoundException("Author not found with id=" + id);
        }
        return optionalAuthor.get();
    }

    public Author addAuthor(AuthorDTO authorDTO) {
        return authorRepository.save(AuthorMapper.INSTANCE.toModel(authorDTO));
    }

    public void deleteAuthor(Long id) throws EntityNotFoundException {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found author by id=" + id));
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(AuthorDTO authorDTO, Long id) throws EntityNotFoundException {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found author by id=" + id));
        Author updateAuthor = AuthorMapper.INSTANCE.toModel(authorDTO);
        updateAuthor.setId(existingAuthor.getId());
        return authorRepository.save(updateAuthor);
    }
}
