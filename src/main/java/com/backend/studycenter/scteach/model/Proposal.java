package com.backend.studycenter.scteach.model;

import com.backend.studycenter.common.model.person.Person;
import com.backend.studycenter.scteach.enumuretion.RequestType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proposal")
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Person fromPersonID;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Person> toPersonsID;    @JoinColumn(name = "toPersonsID")

    private RequestType requestType;
    private String description;
}
