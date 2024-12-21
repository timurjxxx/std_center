package com.backend.studycenter.scteach.model;

import com.backend.studycenter.common.model.person.Person;
import com.backend.studycenter.scteach.enumuretion.RespondStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

//Response (ID,PersonID,RequestID,LocalDateTime,Description)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "respond")
public class Respond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Person personId;
    @OneToOne
    private Proposal proposalId;
    private RespondStatus respondStatus;
    private LocalDateTime time;
    private String description;
}
