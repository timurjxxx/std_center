package com.backend.studycenter.common.service.person;

import com.backend.studycenter.common.dto.person.PersonDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.person.PersonMapper;
import com.backend.studycenter.common.model.person.Person;
import com.backend.studycenter.common.repository.person.PersonRepository;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public ArrayList<Person> getPersons() {
        return (ArrayList<Person>) personRepository.findAll();
    }

    public Person getPersonById(Long personId) throws EntityNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Not found person by id = " + personId));
        return person;
    }

    public Person addPerson(PersonDTO personDTO) {
        return personRepository.save(PersonMapper.INSTANCE.toModel(personDTO));
    }

    public void deletePerson(Long personId) throws EntityNotFoundException {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Not found person by id = " + personId));
        personRepository.delete(person);
    }

    public Person updatePerson(PersonDTO personDTO, Long personId) throws EntityNotFoundException {
        Person existingPerson = personRepository.findById(personId)
                .orElseThrow(() -> new EntityNotFoundException("Not found person by id = " + personId));
        Person updatedPerson = PersonMapper.INSTANCE.toModel(personDTO);
        updatedPerson.setId(existingPerson.getId());
        return personRepository.save(updatedPerson);
    }

    public void clear() {
        personRepository.deleteAll();
    }
}

