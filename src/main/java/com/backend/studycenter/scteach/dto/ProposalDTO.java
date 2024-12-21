package com.backend.studycenter.scteach.dto;

import com.backend.studycenter.common.model.person.Person;
import com.backend.studycenter.scteach.enumuretion.RequestType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProposalDTO {
    private Long id;
    private Person fromPersonID;
    private List<Person> toPersonsID;
    private RequestType requestType;
    private String description;
}
