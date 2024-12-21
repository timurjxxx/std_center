package com.backend.studycenter.scteach.dto;

import com.backend.studycenter.common.model.person.Person;
import com.backend.studycenter.scteach.enumuretion.RespondStatus;
import com.backend.studycenter.scteach.model.Proposal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespondDTO {
    private Long id;
    private Person personId;
    private Proposal proposalId;
    private RespondStatus respondStatus;
    private LocalDateTime time;
    private String description;

}
