package com.backend.studycenter.sccontrol.service;

import com.backend.studycenter.common.dto.person.PersonDTO;
import com.backend.studycenter.common.exception.EntityNotFoundException;
import com.backend.studycenter.common.mapper.person.PersonMapper;
import com.backend.studycenter.common.messaging.EmailService;
import com.backend.studycenter.common.model.person.Person;
import com.backend.studycenter.sccontrol.dto.WebinarDTO;
import com.backend.studycenter.sccontrol.mapper.WebinarMapper;
import com.backend.studycenter.sccontrol.model.Webinar;
import com.backend.studycenter.sccontrol.repository.WebinarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebinarService {
@Autowired
    WebinarRepository webinarRepository;
@Autowired
    EmailService  emailService;
public Webinar getById(Long id) throws EntityNotFoundException {
    Webinar webinar = webinarRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("webinar is not found with this id "+id));
    return webinar;
}
public List<Webinar> getAllWebinar(){
   return (ArrayList<Webinar>) webinarRepository.findAll();
}
public  Webinar addWebinar(WebinarDTO webinarDTO){
    Webinar webinar=WebinarMapper.INSTANCE.toModel(webinarDTO);
    webinarRepository.save(webinar);
    Person speaker =webinar.getSpeaker();
    List<Person> participants =webinar.getParticipants();
    //sendWebinarAlertSpeaker(speaker,webinar);
    //sendWebinarAlertParticipants(participants,webinar);

    return webinar;
}
public  List<PersonDTO> getParticipants(Long webinarId) throws EntityNotFoundException {
Webinar webinar =webinarRepository.findById(webinarId).orElseThrow(()->new EntityNotFoundException("this entity not found"));
List<PersonDTO> personDTOList =new ArrayList<>();
    for (Person person:webinar.getParticipants()
         ) {
        personDTOList.add(PersonMapper.INSTANCE.toDTO(person));
    }
    return  personDTOList;

}

public void deleteWebinar(Long id) throws EntityNotFoundException {
    Webinar webinar =webinarRepository.findById(id).orElseThrow(()->new EntityNotFoundException("webinar is not found with this id : "+ id));
    webinarRepository.delete(webinar);

}
public  Webinar updateWebinar(Long id, WebinarDTO webinarDTO) throws EntityNotFoundException {
    Webinar existingWebinar =webinarRepository.findById(id).orElseThrow(()->new EntityNotFoundException("webinar is not found with this id : "+ id));
    Webinar updatedWebinar = WebinarMapper.INSTANCE.toModel(webinarDTO);
    updatedWebinar.setId(id);
    return webinarRepository.save(updatedWebinar);
}
public void sendWebinarAlertSpeaker(Person person,  Webinar webinar){
    String email =person.getEmail();
    String subject ="Webianr event";
    String text ="Good day dear "+person.getName()+"  you are the speaker of the webinar that will be held in "+webinar.getLocation()
            + ",on "+webinar.getDate() ;
    emailService.sendEmail(email,subject,text);
}
public  void sendWebinarAlertParticipants(List<Person> people,Webinar webinar){
    String subject ="Webianr event";

    for (Person person:people
         ) {
        String text ="Good day dear "+person.getName()+"  you are the participant of the webinar that will be held in "+webinar.getLocation()
                + ",on "+webinar.getDate() ;
        String email =person.getEmail();
        emailService.sendEmail(email,subject,text);

    }
}
    public void clear() {
        webinarRepository.deleteAll();
    }
}
