package com.backend.studycenter.common.controller.person;

import com.backend.studycenter.common.dto.person.PersonDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.person.PersonMapper;
import com.backend.studycenter.common.model.person.Person;
import com.backend.studycenter.common.model.util.util;
import com.backend.studycenter.common.service.person.PersonService;
import java.util.ArrayList;
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
public class PersonController {
    private final Logger logger = LoggerFactory.getLogger(InterviewController.class);
    @Autowired
    private PersonService personService;

    
    @GetMapping(value = "/api/v1/persons")
    public ResponseEntity<ArrayList<PersonDTO>> getPersons() {
        ArrayList<Person> persons = personService.getPersons();
        ArrayList<PersonDTO> personDTOs = new ArrayList<>();
        for (Person person : persons) {
            personDTOs.add(PersonMapper.INSTANCE.toDTO(person));
        }
        if (personDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(personDTOs);
        }
    }

    
    @GetMapping(value = "/api/v1/persons/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable(name = "id") Long personId) throws EntityNotFoundException {
        PersonDTO personDTO = PersonMapper.INSTANCE.toDTO(personService.getPersonById(personId));
        if (personDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(personDTO);
        }
    }

    
    @PostMapping(value = "/api/v1/persons", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO personDTO) {
        try {
            return ResponseEntity.ok(PersonMapper.INSTANCE.toDTO(personService.addPerson(personDTO)));
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @PutMapping(value = "/api/v1/persons/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO, @PathVariable(name = "id") Long personId) throws EntityNotFoundException {
        try {
            return ResponseEntity.ok(PersonMapper.INSTANCE.toDTO(personService.updatePerson(personDTO, personId)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @DeleteMapping(value = "/api/v1/persons/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable(name = "id") Long personId) throws EntityNotFoundException {
        try {
            personService.deletePerson(personId);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Issue is happened in method " + util.getMethodName(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
